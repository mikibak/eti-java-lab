package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Model {
    private EntityManager em;

    public Model(EntityManager em) {
        this.em = em;
    }

    public void print() {
        List<Beer> beers = em.createQuery("SELECT p FROM Beer p", Beer.class).getResultList();
        List<Brewery> breweries = em.createQuery("SELECT p FROM Brewery p", Brewery.class).getResultList();

        System.out.println();

        for(Beer beer : beers) {
            System.out.println(beer.toString());
        }

        System.out.println();

        for(Brewery brewery : breweries) {
            System.out.println(brewery.toString());
        }

        System.out.println();
    }

    public void add(Brewery object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public void add(Beer object) {
        Brewery brewery = (Brewery)em.find(Brewery.class, (Object)object.getBrowar().getName());
        em.getTransaction().begin();
        em.persist(object);
        em.refresh(brewery);
        em.getTransaction().commit();
    }

    public void deletePiwo(String piwoName) {
        em.getTransaction().begin();
        Beer beer = em.find(Beer.class, piwoName);
        if (beer != null) {
            em.remove(beer);
            System.out.println("Beer zostało usunięte.");
        } else {
            System.out.println("Nie znaleziono piwa o podanej nazwie.");
        }

        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

    public void deleteBrowar(String browarName) {
        em.getTransaction().begin();
        Brewery brewery = em.find(Brewery.class, browarName);
        if (brewery != null) {
            em.remove(brewery);
            System.out.println("Brewery został usunięty.");
        } else {
            System.out.println("Nie znaleziono browaru o podanej nazwie.");
        }

        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

    public List<Brewery> getBrowarsWithCheaperThan(long price) {
        String query = "SELECT b FROM Brewery b JOIN b.piwos p WHERE p.cena < :maxPrice";
        TypedQuery<Brewery> typedQuery = em.createQuery(query, Brewery.class);
        typedQuery.setParameter("maxPrice", price);

        return typedQuery.getResultList();
    }

    public List<Beer> pobierzPiwaZCenaNizsza(EntityManager em, long cena) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> piwoRoot = query.from(Beer.class);

        query.select(piwoRoot)
                .where(cb.lt(piwoRoot.get("cena"), cena));

        TypedQuery<Beer> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    public List<Beer> pobierzPiwaZCenaWiekszaDlaBrowaru(EntityManager em, String browarName, long cena) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Beer> query = cb.createQuery(Beer.class);
        Root<Beer> piwoRoot = query.from(Beer.class);

        Join<Beer, Brewery> browarJoin = piwoRoot.join("browar");

        query.select(piwoRoot)
                .where(cb.and(
                        cb.equal(browarJoin.get("name"), browarName),
                        cb.gt(piwoRoot.get("cena"), cena)
                ));

        TypedQuery<Beer> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
