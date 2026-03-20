package com.healthcentre.service;

import com.healthcentre.exception.DuplicateIdException;
import com.healthcentre.exception.InvalidInputException;
import com.healthcentre.exception.StaffNotFoundException;
import com.healthcentre.model.Doctor;
import com.healthcentre.model.Receptionist;
import com.healthcentre.model.StaffMember;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StaffService — singleton service holding all business logic.
 * Acts as the in-memory store for staff members.
 */
public class StaffService {

    private static final StaffService INSTANCE = new StaffService();
    private static final int STAFF_LIMIT = 50;

    private final List<StaffMember> staffList = new ArrayList<>();

    private StaffService() {}

    public static StaffService getInstance() {
        return INSTANCE;
    }

    // ── GET ALL ──────────────────────────────────────────────────────────────

    public List<StaffMember> getAllStaff() {
        return new ArrayList<>(staffList);
    }

    // ── GET SORTED BY NAME ───────────────────────────────────────────────────

    public List<StaffMember> getStaffSortedByName() {
        return staffList.stream()
                .sorted(Comparator.comparing(StaffMember::getSurName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(StaffMember::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // ── GET BY ID ────────────────────────────────────────────────────────────

    public StaffMember getStaffById(String staffId) {
        return staffList.stream()
                .filter(m -> m.getStaffId().equalsIgnoreCase(staffId))
                .findFirst()
                .orElseThrow(() -> new StaffNotFoundException(staffId));
    }

    // ── ADD DOCTOR ───────────────────────────────────────────────────────────

    public Doctor addDoctor(Doctor doctor) {
        validateStaffLimit();
        validateNoDuplicate(doctor.getStaffId());
        validateCommonFields(doctor);

        if (doctor.getLicenceNumber() == null || doctor.getLicenceNumber().isBlank()) {
            throw new InvalidInputException("Licence Number", "must not be empty");
        }
        if (doctor.getSpecialization() == null || doctor.getSpecialization().isBlank()) {
            throw new InvalidInputException("Specialization", "must not be empty");
        }
        if (doctor.getNumberOfConsultationPerWeek() < 0 || doctor.getNumberOfConsultationPerWeek() > 100) {
            throw new InvalidInputException("Consultations per Week", "must be between 0 and 100");
        }

        staffList.add(doctor);
        return doctor;
    }

    // ── ADD RECEPTIONIST ─────────────────────────────────────────────────────

    public Receptionist addReceptionist(Receptionist receptionist) {
        validateStaffLimit();
        validateNoDuplicate(receptionist.getStaffId());
        validateCommonFields(receptionist);

        if (receptionist.getDeskNumber() < 1 || receptionist.getDeskNumber() > 999) {
            throw new InvalidInputException("Desk Number", "must be between 1 and 999");
        }
        if (receptionist.getHoursPerWeek() < 1 || receptionist.getHoursPerWeek() > 80) {
            throw new InvalidInputException("Hours per Week", "must be between 1 and 80");
        }

        staffList.add(receptionist);
        return receptionist;
    }

    // ── REMOVE ───────────────────────────────────────────────────────────────

    public StaffMember removeStaff(String staffId) {
        StaffMember member = getStaffById(staffId);
        staffList.remove(member);
        return member;
    }

    // ── STATISTICS ───────────────────────────────────────────────────────────

    public Map<String, Object> getStatistics() {
        int total = staffList.size();
        long doctors = staffList.stream().filter(m -> m instanceof Doctor).count();
        long receptionists = staffList.stream().filter(m -> m instanceof Receptionist).count();

        double doctorPct = total > 0 ? (double) doctors / total * 100 : 0;
        double receptionistPct = total > 0 ? (double) receptionists / total * 100 : 0;

        double avgConsultations = staffList.stream()
                .filter(m -> m instanceof Doctor)
                .mapToInt(m -> ((Doctor) m).getNumberOfConsultationPerWeek())
                .average().orElse(0);

        double avgHours = staffList.stream()
                .filter(m -> m instanceof Receptionist)
                .mapToInt(m -> ((Receptionist) m).getHoursPerWeek())
                .average().orElse(0);

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStaff", total);
        stats.put("totalDoctors", doctors);
        stats.put("totalReceptionists", receptionists);
        stats.put("doctorPercentage", Math.round(doctorPct * 10.0) / 10.0);
        stats.put("receptionistPercentage", Math.round(receptionistPct * 10.0) / 10.0);
        stats.put("avgConsultationsPerDoctorPerWeek", Math.round(avgConsultations * 10.0) / 10.0);
        stats.put("avgHoursPerReceptionistPerWeek", Math.round(avgHours * 10.0) / 10.0);
        stats.put("staffLimit", STAFF_LIMIT);
        return stats;
    }

    // ── PRIVATE HELPERS ──────────────────────────────────────────────────────

    private void validateStaffLimit() {
        if (staffList.size() >= STAFF_LIMIT) {
            throw new InvalidInputException("Staff", "maximum limit of " + STAFF_LIMIT + " reached");
        }
    }

    private void validateNoDuplicate(String staffId) {
        boolean exists = staffList.stream()
                .anyMatch(m -> m.getStaffId().equalsIgnoreCase(staffId));
        if (exists) {
            throw new DuplicateIdException(staffId);
        }
    }

    private void validateCommonFields(StaffMember member) {
        if (member.getStaffId() == null || member.getStaffId().isBlank()) {
            throw new InvalidInputException("Staff ID", "must not be empty");
        }
        if (member.getName() == null || member.getName().isBlank()) {
            throw new InvalidInputException("Name", "must not be empty");
        }
        if (member.getSurName() == null || member.getSurName().isBlank()) {
            throw new InvalidInputException("Surname", "must not be empty");
        }
        if (member.getDob() == null || member.getDob().isBlank()) {
            throw new InvalidInputException("Date of Birth", "must not be empty (format: YYYY-MM-DD)");
        }
        if (member.getPhoneNo() == null || member.getPhoneNo().isBlank()) {
            throw new InvalidInputException("Phone Number", "must not be empty");
        }
    }
}
