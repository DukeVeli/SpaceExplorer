package space.space.services.services;

import space.space.services.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
