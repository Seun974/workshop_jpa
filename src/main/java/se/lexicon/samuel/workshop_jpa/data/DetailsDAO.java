package se.lexicon.samuel.workshop_jpa.data;

import se.lexicon.samuel.workshop_jpa.entity.Details;

import java.util.Collection;

public interface DetailsDAO {
    Details findById(Integer detailsId);
    Collection<Details> findAll();
    Details create(Details details);
    Details update (Details details);
    void delete(Integer detailsId);

}
