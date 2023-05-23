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
        List<Piwo> piwos = em.createQuery("SELECT p FROM Piwo p", Piwo.class).getResultList();
        List<Browar> browars = em.createQuery("SELECT p FROM Browar p", Browar.class).getResultList();

        System.out.println();

        for(Piwo piwo : piwos) {
            System.out.println(piwo.toString());
        }

        System.out.println();

        for(Browar browar : browars) {
            System.out.println(browar.toString());
        }

        System.out.println();
    }

    public void add(Browar object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public void add(Piwo object) {
        Browar browar = (Browar)em.find(Browar.class, (Object)object.getBrowar().getName());
        //browar.addPiwo(object);
        em.getTransaction().begin();
        em.persist(object);
        em.refresh(browar);
        em.getTransaction().commit();
    }

    public void deletePiwo(String piwoName) {
        em.getTransaction().begin();
        Piwo piwo = em.find(Piwo.class, piwoName);
        if (piwo != null) {
            em.remove(piwo);
            System.out.println("Piwo zostało usunięte.");
        } else {
            System.out.println("Nie znaleziono piwa o podanej nazwie.");
        }

        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

    public void deleteBrowar(String browarName) {
        em.getTransaction().begin();
        Browar browar = em.find(Browar.class, browarName);
        if (browar != null) {
            em.remove(browar);
            System.out.println("Browar został usunięty.");
        } else {
            System.out.println("Nie znaleziono browaru o podanej nazwie.");
        }

        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

    public List<Browar> getBrowarsWithCheaperThan(long price) {
        String query = "SELECT b FROM Browar b JOIN b.piwos p WHERE p.cena < :maxPrice";
        TypedQuery<Browar> typedQuery = em.createQuery(query, Browar.class);
        typedQuery.setParameter("maxPrice", price);

        return typedQuery.getResultList();
    }

    public List<Piwo> pobierzPiwaZCenaNizsza(EntityManager em, long cena) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Piwo> query = cb.createQuery(Piwo.class);
        Root<Piwo> piwoRoot = query.from(Piwo.class);

        query.select(piwoRoot)
                .where(cb.lt(piwoRoot.get("cena"), cena));

        TypedQuery<Piwo> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    public List<Piwo> pobierzPiwaZCenaWiekszaDlaBrowaru(EntityManager em, String browarName, long cena) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Piwo> query = cb.createQuery(Piwo.class);
        Root<Piwo> piwoRoot = query.from(Piwo.class);

        Join<Piwo, Browar> browarJoin = piwoRoot.join("browar");

        query.select(piwoRoot)
                .where(cb.and(
                        cb.equal(browarJoin.get("name"), browarName),
                        cb.gt(piwoRoot.get("cena"), cena)
                ));

        TypedQuery<Piwo> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
