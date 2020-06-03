package org.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apply {

    @EmbeddedId
    private UserJobId id;

    boolean isAccepted;

    public Apply(UserJobId userJobId){
        id=userJobId;
        isAccepted= false;
    }


}
