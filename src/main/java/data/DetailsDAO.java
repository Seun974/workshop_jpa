package data;

import entity.Details;

import java.util.Collection;

public interface DetailsDAO {
    Details findById(Integer detailsId);
    Collection<Details> findAll();
    Details create(Details details);
    Details update (Details details);
    void delete(Integer detailsId);

}
