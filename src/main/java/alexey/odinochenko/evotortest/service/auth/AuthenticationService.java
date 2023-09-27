package alexey.odinochenko.evotortest.service.auth;

import alexey.odinochenko.evotortest.data.request.AuthenticationRequest;
import alexey.odinochenko.evotortest.data.request.RegisterRequest;
import alexey.odinochenko.evotortest.data.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
