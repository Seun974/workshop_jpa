package data;

import entity.Details;

import java.util.Collection;

public interface DetailsDAO {
    Details findById(Integer detailsId);
    Collection<Details> findAll();
    Details create(Details details);
    Details update (Details details);
    boolean delete(Integer detailsId);//take not that this is void and it returns nothing

}
