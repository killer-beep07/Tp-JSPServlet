 package service;

import dao.IDao;
import entities.Machine;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class MachineService implements IDao<Machine> {

    

    @Override
    public boolean create(Machine o)  {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }

        }
        return etat;
    }

    @Override
    public boolean update(Machine o)  {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public boolean delete(Machine o)  {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public Machine findById(int id)  {
        Session session = null;
        Transaction tx = null;
        Machine machine = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            machine = (Machine) session.get(Machine.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findAll()  {
        Session session = null;
        Transaction tx = null;
        List<Machine> machines = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            machines = session.getNamedQuery("findAllMachines").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machines;
    }

    @Override
    public Machine findByCode(String code)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Machine o, Machine p) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Machine clientToUpdate = (Machine) session.get(Machine.class, o.getId());

            if (clientToUpdate != null) {
                clientToUpdate.setRef(p.getRef());
                clientToUpdate.setMarque(p.getMarque());
                clientToUpdate.setPrix(p.getPrix());
                clientToUpdate.setSalle(p.getSalle());

                session.update(clientToUpdate); 
                tx.commit();

                return true;
            }
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Erreur de modification de la machine : " + ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return false;
    }

}
