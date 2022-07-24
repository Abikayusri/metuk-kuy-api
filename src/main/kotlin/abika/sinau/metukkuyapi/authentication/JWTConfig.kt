package abika.sinau.metukkuyapi.authentication

import abika.sinau.metukkuyapi.model.customer.Customer
import abika.sinau.metukkuyapi.model.driver.Driver
import abika.sinau.metukkuyapi.utils.Constant.CLAIMS
import abika.sinau.metukkuyapi.utils.Constant.SECRET
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Date
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@EnableWebSecurity
@Configuration
class JWTConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authenticationFilter: AuthenticationFilter

    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, *postPermit.toTypedArray()).permitAll()
                .antMatchers(HttpMethod.GET, *getPermit.toTypedArray()).permitAll()
                .anyRequest().authenticated()

    }

    companion object {
        val postPermit = listOf(
                "/v1/driver/login",
                "/v1/driver/register",
                "/v1/customer/login",
                "/v1/customer/register"
        )

        val getPermit = listOf(
                "/api/ping"
        )

        private val expired = Date(System.currentTimeMillis() + (60_000 * 60 * 24))
        private var subject: String = ""

        fun generateToken(driver: Driver): String {
            subject = driver.id
            val granted = AuthorityUtils.commaSeparatedStringToAuthorityList(driver.username)
            val grantedStream = granted.stream().map { it.authority }.collect(Collectors.toList())

            return Jwts.builder()
                    .setSubject(subject)
                    .claim(CLAIMS, grantedStream)
                    .setExpiration(expired)
                    .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray()), SignatureAlgorithm.HS256)
                    .compact()
        }

        fun generateToken(customer: Customer): String {
            subject = customer.id
            val granted = AuthorityUtils.commaSeparatedStringToAuthorityList(customer.username)
            val grantedStream = granted.stream().map { it.authority }.collect(Collectors.toList())

            return Jwts.builder()
                    .setSubject(subject)
                    .claim(CLAIMS, grantedStream)
                    .setExpiration(expired)
                    .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray()), SignatureAlgorithm.HS256)
                    .compact()
        }

        fun isPermit(request: HttpServletRequest): Boolean {
            val path = request.servletPath
            return postPermit.contains(path) or getPermit.contains(path)
        }
    }
}

// function for get secret
//fun main() {
//    val keys = Keys.secretKeyFor(SignatureAlgorithm.HS256)
//    val secret = Encoders.BASE64.encode(keys.encoded)
//
//    println("Secret is --> $secret")
//}