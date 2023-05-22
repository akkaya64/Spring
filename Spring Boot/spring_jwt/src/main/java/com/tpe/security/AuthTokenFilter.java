package com.tpe.security;

import com.tpe.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//User ve Password valid edilecek
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired // usera ulasabilmek icin enjekte edildi
    private UserDetailsService userDetailsService;


    @Override//Validation islemi
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // jwt tokeni requestin icinden almamiz gerekiyor
        String jwtToken = parseJwt(request);

        try {
            if(jwtToken!=null && jwtUtils.validateToken(jwtToken)) {

                String userName = jwtUtils.getUserNameFromJwtToken(jwtToken); //JWT tokenden Username i cekiyoruz

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);//Validaton bu code ile yapiliyor
                // buradan itibaren authenticate edilmis kullaniciyi context e atiyorum
                UsernamePasswordAuthenticationToken authentication = // Validation ediolmis kullanici detailini context e gonderiyoruz
                        //burada boyle bir user yoksa exception burada atiliyor
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request,response);

    }

    // !!! requestin icindeki JWT tokeni cikartan method
    private String parseJwt(HttpServletRequest request){

        String header =  request.getHeader("Authorization");
        // "Bearer jsdfsfsjfsjfjsgfsgjhwuyt8w8w87.sgfddsgere4reee.546456465464zdfsfswf"
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;

    }

    // !!! alttaki methodun permitAll() dan farki : permitAll() da kimlik kontrolu yapilmayacak
    // end-pointler velirtilirken , shouldNotFilter() da icinde bulundugumuz filtreye
    // girmesini istemedigimiz end-pointleri yaziyoruz
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher antMatcher = new AntPathMatcher();
        return antMatcher.match("/register",request.getServletPath()) ||
                antMatcher.match("/login", request.getServletPath());
    }
}
