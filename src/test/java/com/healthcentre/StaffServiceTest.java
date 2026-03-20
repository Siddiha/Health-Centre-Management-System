package com.healthcentre;

import com.healthcentre.exception.DuplicateIdException;
import com.healthcentre.exception.InvalidInputException;
import com.healthcentre.exception.StaffNotFoundException;
import com.healthcentre.model.Doctor;
import com.healthcentre.model.Receptionist;
import com.healthcentre.model.StaffMember;
import com.healthcentre.service.StaffService;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StaffServiceTest {

    private static final StaffService service = StaffService.getInstance();

    // ── HELPERS ──────────────────────────────────────────────────────────────

    private Doctor sampleDoctor(String id) {
        return new Doctor("John", "Smith", "1985-06-15", "07700900001",
                id, "LIC-001", "Cardiology", 20);
    }

    private Receptionist sampleReceptionist(String id) {
        return new Receptionist("Jane", "Doe", "1990-03-20", "07700900002",
                id, 5, 40);
    }

    @BeforeEach
    void cleanUp() {
        // Remove any leftover staff from previous tests
        List<StaffMember> all = new java.util.ArrayList<>(service.getAllStaff());
        for (StaffMember m : all) {
            service.removeStaff(m.getStaffId());
        }
    }

    // ── ADD DOCTOR ───────────────────────────────────────────────────────────

    @Test
    @Order(1)
    void testAddDoctor_success() {
        Doctor doctor = service.addDoctor(sampleDoctor("D001"));
        assertEquals("D001", doctor.getStaffId());
        assertEquals("Doctor", doctor.getRole());
        assertEquals(1, service.getAllStaff().size());
    }

    @Test
    @Order(2)
    void testAddDoctor_duplicateId_throwsException() {
        service.addDoctor(sampleDoctor("D001"));
        assertThrows(DuplicateIdException.class, () -> service.addDoctor(sampleDoctor("D001")));
    }

    @Test
    @Order(3)
    void testAddDoctor_invalidConsultations_throwsException() {
        Doctor bad = new Doctor("John", "Smith", "1985-06-15", "07700900001",
                "D002", "LIC-002", "Cardiology", 999); // consultations > 100
        assertThrows(InvalidInputException.class, () -> service.addDoctor(bad));
    }

    @Test
    @Order(4)
    void testAddDoctor_missingName_throwsException() {
        Doctor bad = new Doctor("", "Smith", "1985-06-15", "07700900001",
                "D003", "LIC-003", "Cardiology", 10);
        assertThrows(InvalidInputException.class, () -> service.addDoctor(bad));
    }

    // ── ADD RECEPTIONIST ─────────────────────────────────────────────────────

    @Test
    @Order(5)
    void testAddReceptionist_success() {
        Receptionist r = service.addReceptionist(sampleReceptionist("R001"));
        assertEquals("R001", r.getStaffId());
        assertEquals("Receptionist", r.getRole());
        assertEquals(1, service.getAllStaff().size());
    }

    @Test
    @Order(6)
    void testAddReceptionist_invalidHours_throwsException() {
        Receptionist bad = new Receptionist("Jane", "Doe", "1990-03-20", "07700900002",
                "R002", 5, 200); // hoursPerWeek > 80
        assertThrows(InvalidInputException.class, () -> service.addReceptionist(bad));
    }

    // ── GET BY ID ────────────────────────────────────────────────────────────

    @Test
    @Order(7)
    void testGetById_success() {
        service.addDoctor(sampleDoctor("D001"));
        StaffMember found = service.getStaffById("D001");
        assertEquals("D001", found.getStaffId());
    }

    @Test
    @Order(8)
    void testGetById_notFound_throwsException() {
        assertThrows(StaffNotFoundException.class, () -> service.getStaffById("NOTEXIST"));
    }

    // ── REMOVE ───────────────────────────────────────────────────────────────

    @Test
    @Order(9)
    void testRemoveStaff_success() {
        service.addDoctor(sampleDoctor("D001"));
        service.removeStaff("D001");
        assertEquals(0, service.getAllStaff().size());
    }

    @Test
    @Order(10)
    void testRemoveStaff_notFound_throwsException() {
        assertThrows(StaffNotFoundException.class, () -> service.removeStaff("GHOST"));
    }

    // ── SORTED ───────────────────────────────────────────────────────────────

    @Test
    @Order(11)
    void testGetStaffSortedByName() {
        service.addDoctor(new Doctor("Charlie", "Zara", "1980-01-01", "07700900003",
                "D010", "LIC-010", "Neurology", 10));
        service.addDoctor(new Doctor("Alice", "Adams", "1982-01-01", "07700900004",
                "D011", "LIC-011", "Dermatology", 5));
        service.addReceptionist(new Receptionist("Bob", "Brown", "1991-01-01", "07700900005",
                "R010", 2, 35));

        List<StaffMember> sorted = service.getStaffSortedByName();
        assertEquals("Adams", sorted.get(0).getSurName());
        assertEquals("Brown", sorted.get(1).getSurName());
        assertEquals("Zara", sorted.get(2).getSurName());
    }

    // ── STATISTICS ───────────────────────────────────────────────────────────

    @Test
    @Order(12)
    void testGetStatistics() {
        service.addDoctor(sampleDoctor("D001"));
        service.addReceptionist(sampleReceptionist("R001"));

        Map<String, Object> stats = service.getStatistics();
        assertEquals(2, stats.get("totalStaff"));
        assertEquals(1L, stats.get("totalDoctors"));
        assertEquals(1L, stats.get("totalReceptionists"));
        assertEquals(50.0, stats.get("doctorPercentage"));
        assertEquals(50.0, stats.get("receptionistPercentage"));
    }
}
