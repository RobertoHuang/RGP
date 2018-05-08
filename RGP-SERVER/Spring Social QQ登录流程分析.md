# Spring Social流程

## OAuth2.0认证流程示意图
![OAuth2.0认证流程示意图](http://img-blog.csdn.net/20180508101444324?watermark/2/text/Ly9ibG9nLmNzZG4ubmV0L1JvYmVydG9IdWFuZw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

在标准的OAuth2协议中步骤1-6是固定的，只有最后一步不同的服务提供商返回的用户信息是不同的

## 使用Spring Social

### 导入依赖
```
<!-- Spring-Social Start -->
<dependency>
    <groupId>org.springframework.social</groupId>
    <artifactId>spring-social-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.social</groupId>
    <artifactId>spring-social-core</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.social</groupId>
    <artifactId>spring-social-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.social</groupId>
    <artifactId>spring-social-web</artifactId>
</dependency>
<!-- Spring-Social End -->
```
## 源码分析

### 查看过滤器链
```
FilterSecurityInterceptor:doFilter() 打断点查看chain情况
```
### 请求URL地址组成
```
/qqLogin/callback.do => {filterProcessesUrl}/{providerId}
```
### 个性化SocialAuthenticationFilter配置
```
SpringSocialConfigurer:configure()方法中对SocialAuthenticationFilter进行了定义
但在configure()方法将SocialAuthenticationFilter加到Security过滤器链之前调用了postProcess(filter)方法
所以我们需要自定义类继承SpringSocialConfigurer并重写postProcess()方法对Filter进行个性化配置

public class RGPSpringSocialConfiguration extends SpringSocialConfigurer {
    private String filterProcessesUrl;

    public RGPSpringSocialConfiguration(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl("/register");
        return (T) filter;
    }
}
```
### 判断SocialAuthenticationFilter是否生效
```
protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
    String providerId = getRequestedProviderId(request);
    if (providerId != null){
        Set<String> authProviders = authServiceLocator.registeredAuthenticationProviderIds();
        return authProviders.contains(providerId);
    }
    return false;
}
```
### SocialAuthenticationFilter执行流程
```
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    // 1.判断用户是否允许授权
    if (detectRejection(request)) {
        if (logger.isDebugEnabled()) {
            logger.debug("A rejection was detected. Failing authentication.");
        }
        throw new SocialAuthenticationException("Authentication failed because user rejected authorization.");
    }
    Authentication auth = null;
    // 2.获取所有的社交配置providerId
    Set<String> authProviders = authServiceLocator.registeredAuthenticationProviderIds();
    // 3.根据请求获取当前的是那种类型的社交登录
    String authProviderId = getRequestedProviderId(request);
    // 4.判断是否系统中是否配置当前社交providerId
    if (!authProviders.isEmpty() && authProviderId != null && authProviders.contains(authProviderId)) {
        // 5.获取当前社交的处理类即OAuth2AuthenticationService用于获取Authentication
        SocialAuthenticationService<?> authService = authServiceLocator.getAuthenticationService(authProviderId);
        // 6.获取SocialAuthenticationToken
        auth = attemptAuthService(authService, request, response);
        if (auth == null) {
            throw new AuthenticationServiceException("authentication failed");
        }
    }
    return auth;
}

private Authentication attemptAuthService(final SocialAuthenticationService<?> authService, final HttpServletRequest request, HttpServletResponse response) throws SocialAuthenticationRedirectException, AuthenticationException {
    // 7.获取SocialAuthenticationToken
    final SocialAuthenticationToken token = authService.getAuthToken(request, response);
    if (token == null) {
        return null;
    }

    Assert.notNull(token.getConnection());
    // 8.从SecurityContext获取Authentication判断是否认证
    Authentication auth = getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
        // 9.进行认证
        return doAuthentication(authService, request, token);
    } else {
        // 10.返回当前的登录账户的一些信息
        addConnection(authService, request, token, auth);
        return null;
    }        
} 
```
### OAuth2AuthenticationService#getAuthToken分析
```
public SocialAuthenticationToken getAuthToken(HttpServletRequest request, HttpServletResponse response) throws SocialAuthenticationRedirectException {
    // 1.获取code
    String code = request.getParameter("code");
    // 2.判断code值
    if (!StringUtils.hasText(code)) {
        // 3.如果code不存在则抛出SocialAuthenticationRedirectException 
        // SocialAuthenticationRedirectException包含重定向的URL地址 外部会捕获该异常并重定向
        OAuth2Parameters params =  new OAuth2Parameters();
        params.setRedirectUri(buildReturnToUrl(request));
        setScope(request, params);
        params.add("state", generateState(connectionFactory, request));
        addCustomParameters(params);
        throw new SocialAuthenticationRedirectException(getConnectionFactory().getOAuthOperations().buildAuthenticateUrl(params));
    } else if (StringUtils.hasText(code)) {
        try {
            4.如果code存在则根据code获得access_token(登录回调是会带Code回来)
            String returnToUrl = buildReturnToUrl(request);
            AccessGrant accessGrant = getConnectionFactory().getOAuthOperations().exchangeForAccess(code, returnToUrl, null);
            // TODO avoid API call if possible (auth using token would be fine)
            // 5.用access_token获取用户的信息并返回spring Social标准信息模型
            Connection<S> connection = getConnectionFactory().createConnection(accessGrant);
            // 6.使用返回的用户信息构建SocialAuthenticationToken
            return new SocialAuthenticationToken(connection, null);
        } catch (RestClientException e) {
            logger.debug("failed to exchange for access", e);
            return null;
        }
    } else {
        return null;
    }
}
```
### SocialAuthenticationFilter#doAuthentication分析
```
private Authentication doAuthentication(SocialAuthenticationService<?> authService, HttpServletRequest request, SocialAuthenticationToken token) {
    try {
        if (!authService.getConnectionCardinality().isAuthenticatePossible()) 
            return null;
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        // AuhenticationManage认证流程 => 将调用SocialAuthenticationProvider的authenticate()
        Authentication success = getAuthenticationManager().authenticate(token);
        Assert.isInstanceOf(SocialUserDetails.class, success.getPrincipal(), "unexpected principle type");
        updateConnections(authService, token, success);            
        return success;
    } catch (BadCredentialsException e) {
        // connection unknown, register new user?
        if (signupUrl != null) {
            // store ConnectionData in session and redirect to register page
            sessionStrategy.setAttribute(new ServletWebRequest(request), ProviderSignInAttempt.SESSION_ATTRIBUTE, new ProviderSignInAttempt(token.getConnection()));
            throw new SocialAuthenticationRedirectException(buildSignupUrl(request));
        }
        throw e;
    }
}
```
###  SocialAuthenticationProvider#authenticate分析
```
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(SocialAuthenticationToken.class, authentication, "unsupported authentication type");
    Assert.isTrue(!authentication.isAuthenticated(), "already authenticated");
    SocialAuthenticationToken authToken = (SocialAuthenticationToken) authentication;
    // 1.从SocialAuthenticationToken中获取providerId(表示当前是那个第三方登录)
    String providerId = authToken.getProviderId();
    // 2.从SocialAuthenticationToken中获取获取用户信息 即ApiAdapter设置的用户信息
    Connection<?> connection = authToken.getConnection();
    // 3.从UserConnection表中查询数据
    String userId = toUserId(connection);
    if (userId == null) {
        throw new BadCredentialsException("Unknown access token");
    }
    // 4.调用我们自定义的MyUserDetailsService查询
    UserDetails userDetails = userDetailsService.loadUserByUserId(userId);
    if (userDetails == null) {
        throw new UsernameNotFoundException("Unknown connected account id");
    }
    return new SocialAuthenticationToken(connection, userDetails, authToken.getProviderAccountData(), getAuthorities(providerId, userDetails));
}
```
### JdbcUsersConnectionRepository#findUserIdsWithConnection分析
```
建表语句spring-social-core:org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository.sql

public List<String> findUserIdsWithConnection(Connection<?> connection) {
    ConnectionKey key = connection.getKey();
    List<String> localUserIds = jdbcTemplate.queryForList("select userId from " + tablePrefix + "UserConnection where providerId = ? and providerUserId = ?", String.class, key.getProviderId(), key.getProviderUserId());    
    // 如果当前用户为第一次登录 并且配置了conncetionSignUp则自动注册系统用户    
    if (localUserIds.size() == 0 && connectionSignUp != null) {
        String newUserId = connectionSignUp.execute(connection);
        if (newUserId != null){
            createConnectionRepository(newUserId).addConnection(connection);
            return Arrays.asList(newUserId);
        }
    }
    return localUserIds;
}
```