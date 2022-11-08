package com.dev.clinic.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Medicine;
import com.dev.clinic.model.Regulation;
import com.dev.clinic.model.Role;
import com.dev.clinic.model.Unit;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.RoleRepository;
import com.dev.clinic.service.CertificateService;
import com.dev.clinic.service.MedicineService;
import com.dev.clinic.service.RegulationService;
import com.dev.clinic.service.UnitService;
import com.dev.clinic.service.UserService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Cloudinary cloudinary;

    // #region user
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("sex") String sex,
            @RequestParam("email") String email,
            @RequestParam("comfirmPassword") String comfirmPassword,
            @RequestParam("phone") String phone,
            @RequestParam("roles") List<Integer> roles) {

        String img;
        User user = new User();
        try {
            Map resolve;
            resolve = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            img = (String) resolve.get("secure_url");
            user.setAvatar(img);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setSex(sex);
            user.setEmail(email);
            user.setComfirmPassword(comfirmPassword);
            for (Integer integer : roles) {
                Optional<Role> rl = this.roleRepository.findById(integer);
                user.addRole(rl.get());
            }
        } catch (IOException ex) {
            throw new NotFoundException("Lỗi!!");
        }
        UserDto newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("comfirmPassword") String comfirmPassword,
            @RequestParam("phone") String phone) {

        String img;
        User user = new User();
        try {

            Map resolve;
            resolve = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            img = (String) resolve.get("secure_url");
            user.setAvatar(img);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setComfirmPassword(comfirmPassword);
        } catch (IOException ex) {
            throw new NotFoundException("anh loi nhe");
        }
        UserDto newUser = this.userService.createAUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {
        if (this.userService.deleteUser(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,
            @RequestBody User user, @RequestBody List<Role> roles) {
        User updaUser = this.userService.updateAUser(id, user, roles);

        return ResponseEntity.ok(updaUser);
    }
    // #endregion

    // #region medicines
    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getMedicines(@RequestParam(required = false, defaultValue = "") String name) {
        List<Medicine> medicines = this.medicineService.getMedicines(name);
        return ResponseEntity.ok(medicines);
    }

    @PostMapping("/medicines")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        try {
            Medicine newMedicine = this.medicineService.createMedicine(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMedicine);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<Boolean> deleteMedicine(@PathVariable long id) {
        if (this.medicineService.deleteMedicine(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/medicines/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable long id,
            @RequestBody Medicine medicine) {
        Medicine updaMedicine = this.medicineService.updateMedicine(id, medicine);

        return ResponseEntity.ok(updaMedicine);
    }
    // #endregion

    // #region certificates
    @GetMapping("/certificates")
    public ResponseEntity<List<Certificate>> getCertificates() {
        List<Certificate> certificates = this.certificateService.getCertificates();
        return ResponseEntity.ok(certificates);
    }

    @PostMapping("/certificates")
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) {
        try {
            Certificate newCertificate = this.certificateService.createACertificate(certificate);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/certificates/{id}")
    public ResponseEntity<Boolean> deleteCertificates(@PathVariable long id) {
        if (this.certificateService.deleteCertifcate(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/certificates/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable long id,
            @RequestBody Certificate certificate) {
        Certificate updaCertificate = this.certificateService.updateACertificate(id, certificate);

        return ResponseEntity.ok(updaCertificate);
    }
    // #endregion

    // #region units
    @GetMapping("/units")
    public ResponseEntity<List<Unit>> getUnits(@RequestParam(required = false, defaultValue = "") String name) {
        List<Unit> units = this.unitService.getUnits(name);
        return ResponseEntity.ok(units);
    }

    @PostMapping("/units")
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
        try {
            Unit newUnit = this.unitService.createUnit(unit);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUnit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/units/{id}")
    public ResponseEntity<Boolean> deleteUnit(@PathVariable int id) {
        if (this.unitService.deleteUnit(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/units/{id}")
    public ResponseEntity<Unit> updateUnit(@PathVariable int id,
            @RequestBody Unit Unit) {
        Unit updaUnit = this.unitService.updateUnit(id, Unit);

        return ResponseEntity.ok(updaUnit);
    }
    // #endregion

    // #region regulations
    @GetMapping("/regulations")
    public ResponseEntity<List<Regulation>> getRegulations() {
        List<Regulation> Regulations = this.regulationService.getRegulations();
        return ResponseEntity.ok(Regulations);
    }

    @PostMapping("/regulations")
    public ResponseEntity<Regulation> createRegulation(@RequestBody Regulation regulation) {
        try {
            Regulation newRegulation = this.regulationService.createRegulation(regulation);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRegulation);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/regulations/{id}")
    public ResponseEntity<Boolean> deleteRegulation(@PathVariable Long id) {
        if (this.regulationService.deleteRegulation(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/regulations/{id}")
    public ResponseEntity<Regulation> updateRegulation(@PathVariable Long id,
            @RequestBody Regulation regulation) {
        Regulation updaRegulation = this.regulationService.updateRegulation(id, regulation);

        return ResponseEntity.ok(updaRegulation);
    }
    // #endregion
}
