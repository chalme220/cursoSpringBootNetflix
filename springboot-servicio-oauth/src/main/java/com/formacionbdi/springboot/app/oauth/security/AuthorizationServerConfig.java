package com.formacionbdi.springboot.app.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	//desde SpringSecurityConfig por Bean
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	//pasamos de AccessTokenConverter a JwtAccessTokenConverter
	@Bean//necesario para el componente
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}

	//configurar el AuthenticationManager, el token storage y converter(guarda los claims)
	@Override//oauth/token
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		
		.tokenEnhancer(tokenEnhancerChain);
	}
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")//metodo de autenticacion /oauth/token
		.checkTokenAccess("isAuthenticated()");//token como Barer//basic el usuario y la contraseña
	}

	

	// si son varias apps front tenemos que registrarlas sus credenciales
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		//registrar un cliente //identificador de nuestro cliente
		clients.inMemory()
		.withClient(env.getProperty("config.security.oauth.client.id"))
		.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
		.scopes("read","write")//alcance
		.authorizedGrantTypes("password", "refresh_token")//como vamos a obtener el token;//y obtener un nuevo token antes de que caduque el acutual
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
		//si tenemos mas clientes
		/*.and()
		.withClient("androidApp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write")//alcance
		.authorizedGrantTypes("password", "refresh_token")//como vamos a obtener el token;//y obtener un nuevo token antes de que caduque el acutual
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600)
		*/
		
	}
	
	
}
