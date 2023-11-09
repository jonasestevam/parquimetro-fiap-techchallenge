// package com.jonasestevam.parquimetro.controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.jonasestevam.parquimetro.models.OngoingParkSession;
// import com.jonasestevam.parquimetro.services.OngoingParkSessionService;

// @RestController
// @RequestMapping("/api/ongoingParkSessions")
// public class OngoingParkSessionController {

//     @Autowired
//     OngoingParkSessionService ongoingParkSessionService;

//     @PostMapping
//     public ResponseEntity<OngoingParkSession> createOngoingParkSession(
//             @RequestBody OngoingParkSession ongoingParkSessionDTO) {
//         return ResponseEntity.ok(ongoingParkSessionService.saveOngoingParkSession(ongoingParkSessionDTO));
//     }

//     @GetMapping
//     public ResponseEntity<List<OngoingParkSession>> getAllOngoingParkSessions() {
//         List<OngoingParkSession> ongoingParkSessions = ongoingParkSessionService.getAllOngoingParkSessions();
//         return ResponseEntity.ok(ongoingParkSessions);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<OngoingParkSession> getOngoingParkSessionById(@PathVariable String id) throws Exception {
//         OngoingParkSession ongoingParkSession = ongoingParkSessionService.getOngoingParkSessionById(id);
//         return ResponseEntity.ok(ongoingParkSession);
//     }
// }
