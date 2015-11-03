package br.edu.ufrn.imd.coopuni.util;

import br.edu.ufrn.imd.coopuni.service.MemberService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreza on 01/11/15.
 */
@RequestScoped
public class SecurityFilter {

    @Inject
    MemberService memberService;


    public boolean isUserAllowed (HttpHeaders hHeaders)
    {
        String user = hHeaders.getRequestHeader("user").get(0);
        String token = hHeaders.getRequestHeader("token").get(0);

        if (memberService.isAuthorizationTokenValid(user, token))
        {
            return true;
        }
        return false; // 401
    }

}
