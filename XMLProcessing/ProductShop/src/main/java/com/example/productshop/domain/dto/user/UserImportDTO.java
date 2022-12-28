package com.example.productshop.domain.dto.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDTO {

    @XmlElement(name = "user")
    private List<UserNameDTO> users;

    public List<UserNameDTO> getUsers() {
        return users;
    }
}
