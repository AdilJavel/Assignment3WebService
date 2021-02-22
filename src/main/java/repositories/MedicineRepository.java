package repositories;

import data.interfaces.IDB;
import entities.Medicine;
import repositories.interfaces.IMedicineRepository;

import javax.inject.Inject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class MedicineRepository implements IMedicineRepository {
    @Inject
    private IDB db;


    @Override
    public boolean createMedicine(Medicine medicine) {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql1 = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE name LIKE ?";
            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setString(1, medicine.getName() );
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                return false;

            } else {

                String sql = "INSERT INTO medicine(name, price, expiration_date, manufacturer, byorder) VALUES (?,?,?,?,?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, medicine.getName());
                st.setInt(2, medicine.getPrice());
                st.setDate(3, Date.valueOf(medicine.getDate()));
                st.setString(4, medicine.getManufacturer());
                st.setBoolean(5, medicine.isByorder());

                st.execute();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Medicine getByID(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        LocalDate.parse(rs.getString("expiration_date")),
                        rs.getString("manufacturer"),
                        rs.getBoolean("byorder"));

                return medicine;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public Medicine getByName(String name) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE name LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%" + name + "%");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        LocalDate.parse(rs.getString("expiration_date")),
                        rs.getString("manufacturer"),
                        rs.getBoolean("byorder"));

                return medicine;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean removeByID(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM medicine WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            if(st.execute())
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public List<Medicine> getAllMedicine() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM medicine";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Medicine> medicines = new LinkedList<>();
            while (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        LocalDate.parse(rs.getString("expiration_date")),
                        rs.getString("manufacturer"),
                        rs.getBoolean("byorder"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean checkOrder(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("byorder")==true) {
                    return true;
                } else return false;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Medicine> getByManufacturer(String manufacturer) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE manufacturer LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%" + manufacturer + "%");
            ResultSet rs = st.executeQuery();
            List<Medicine> medicines = new LinkedList<>();
            while (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        LocalDate.parse(rs.getString("expiration_date")),
                        rs.getString("manufacturer"),
                        rs.getBoolean("byorder"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Medicine> getExpired() {
        LocalDateTime now = LocalDateTime.now();
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,price,expiration_date,manufacturer,byorder FROM medicine WHERE expiration_date<'now'";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            List<Medicine> medicines = new LinkedList<>();
            while (rs.next()) {
                Medicine medicine = new Medicine(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        LocalDate.parse(rs.getString("expiration_date")),
                        rs.getString("manufacturer"),
                        rs.getBoolean("byorder"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
