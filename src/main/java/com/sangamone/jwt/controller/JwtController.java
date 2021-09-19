package com.sangamone.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sangamone.jwt.model.JwtRequest;
import com.sangamone.jwt.model.JwtResponse;
import com.sangamone.jwt.service.UserService;
import com.sangamone.jwt.utility.JWTUtility;

@RestController
public class JwtController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
@Autowired
private JWTUtility jwtUtility;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
           System.out.println(jwtRequest);
        try {
           this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
        	e.printStackTrace();
        	throw new Exception("INVALID_CREDENTIALS", e);
        }

         UserDetails userDetails= this.userService.loadUserByUsername(jwtRequest.getUsername());

         String token =this.jwtUtility.generateToken(userDetails);
         System.out.println("JWT" + token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
	


