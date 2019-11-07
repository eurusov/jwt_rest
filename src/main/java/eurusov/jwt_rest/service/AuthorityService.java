package eurusov.jwt_rest.service;

import eurusov.jwt_rest.model.Authority;

import java.util.List;

public interface AuthorityService {

    Authority createNew(String authority, Integer sortOrder);

    List<Authority> getAll();

}
