package com.emapaez.storepay.features.user;

import com.emapaez.storepay.features.user.domain.UserEntity;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class UserSpecification {

    public static PredicateSpecification<UserEntity> nameContains(String name){
        return (root, cb) -> name == null || name.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static PredicateSpecification<UserEntity> lastNameContains(String lastName){
        return (root, cb) -> lastName == null || lastName.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    /// ----------------------- MAP DNI
    private static String mapDni(String dni){
        String clear = dni.replaceAll("\\D", "");
        return String.format("%08d", Long.parseLong(clear));
    }
    /// ----------------------- MAP DNI

    public static PredicateSpecification<UserEntity> dniEquals(String dni){
        return (root, cb) -> dni == null || dni.isBlank()
                ? cb.conjunction()
                : cb.equal(root.get("dni"), mapDni(dni));
    }

    public static PredicateSpecification<UserEntity> emailEquals(String email){
        return (root, cb) -> email == null || email.isBlank()
                ? cb.conjunction()
                : cb.equal(cb.lower(root.get("email").get("value")), email.toLowerCase());
    }

    public static PredicateSpecification<UserEntity> phoneEquals(Long phone){
        return (root , cb)-> phone == null
                ? cb.conjunction()
                :cb.equal(root.get("phoneNumber"), phone);
    }

    public static PredicateSpecification<UserEntity> storeEquals(String nameStore){
        return(root, cb) -> nameStore == null || nameStore.isBlank()
                ? cb.conjunction()
                : cb.equal(cb.lower(root.get("store").get("name")), nameStore.toLowerCase());
    }
}
