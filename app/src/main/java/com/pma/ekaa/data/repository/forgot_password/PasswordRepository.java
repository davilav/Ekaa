package com.pma.ekaa.data.repository.forgot_password;

import com.pma.ekaa.data.models.Password;

public interface PasswordRepository {

    void setRecoveryPassword(Password password);

}
