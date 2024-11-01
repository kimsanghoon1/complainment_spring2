package complainment.domain;

import complainment.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "complaints",
    path = "complaints"
)
public interface ComplaintRepository
    extends PagingAndSortingRepository<Complaint, Long> {}
