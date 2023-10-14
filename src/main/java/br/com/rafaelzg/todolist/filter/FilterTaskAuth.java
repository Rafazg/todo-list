package br.com.rafaelzg.todolist.filter;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rafaelzg.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //Pegar autenticação (Usuario e Senha)
               System.out.println("Chegou no Filter");
               var authorization = request.getHeader("Authorization");
               var authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

               var authString = new String(authDecode);

               System.out.println("Authorization");


               //["Rafazg", "9887"]
               String[] credentials = authString.split(":");
               String username = credentials[0];
               String password = credentials[1];
               System.out.println(username);
               System.out.println(password);


                //Validar Usuário
               var user = this.userRepository.findByUsername(username);
                if (user == null) {
                    response.sendError(401);
                } else {
                //Validar Senha
                   var passwordVeify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                   if (passwordVeify.verified) {
                      filterChain.doFilter(request, response);
                   } else{
                    response.sendError(401);
                   }
                //Segue Viagem

              
                }

                
    }

}
