package eurusov.jwt_rest.service;

import eurusov.jwt_rest.model.Authority;
import eurusov.jwt_rest.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorityService")
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public Authority createNew(String authority, Integer sortOrder) {
        return authorityRepository.save(new Authority(authority, sortOrder));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Authority> getAll() {
        return authorityRepository.findAll(Sort.by(Sort.Direction.ASC, "sortOrder"));
    }
}
