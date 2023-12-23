
package com.company.dao;

import com.company.entity.AuthUserOTP;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserOTPDAO extends BaseDAO<AuthUserOTP, String> {
    private static final ThreadLocal<AuthUserOTPDAO> authUserOTPDAOThreadLocal = ThreadLocal.withInitial(AuthUserOTPDAO::new);

    public static AuthUserOTPDAO getInstance() {
        return authUserOTPDAOThreadLocal.get();
    }

    public AuthUserOTP findByUserID(@NonNull String userID) {
        begin();
        AuthUserOTP authUserOTP = em.createQuery("from AuthUserOTP t where t.userID = :userID and not t.deleted", AuthUserOTP.class)
                .setParameter("userID", userID)
                .getSingleResult();
        commit();
        return authUserOTP;
    }
}
