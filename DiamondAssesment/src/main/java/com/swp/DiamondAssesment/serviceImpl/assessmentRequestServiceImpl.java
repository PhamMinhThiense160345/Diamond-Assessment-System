package com.swp.DiamondAssesment.serviceImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.swp.DiamondAssesment.DTO.*;
import com.swp.DiamondAssesment.model.Assessment;
import com.swp.DiamondAssesment.model.AssessmentRequests;
import com.swp.DiamondAssesment.model.AssessmentRequestsDetail;
import com.swp.DiamondAssesment.service.assessmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.DTO.assessmentRequestDTO;
import com.swp.DiamondAssesment.DTO.inspectParameterDTO;
import com.swp.DiamondAssesment.repository.serviceRepository;
import com.swp.DiamondAssesment.repository.paymentRepository;
import com.swp.DiamondAssesment.repository.asrRepository;
import com.swp.DiamondAssesment.repository.asrDetailRepository;
import com.swp.DiamondAssesment.repository.asRepository;
import com.swp.DiamondAssesment.repository.userRepository;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
 public class assessmentRequestServiceImpl implements assessmentRequestService {
    private final serviceRepository serviceRepository;
    private final paymentRepository payRepository;
    private final userRepository userRepository;
    private final asrRepository asrRepository;
    private final asrDetailRepository asrDetailRepository;
    private final asRepository asRepository;


    @Override
    public ResponseEntity<ResponseObject> createRequest(assessmentRequestDTO assessmentRequestDTO) {
        try {
            var service = serviceRepository.findById(assessmentRequestDTO.getService_id()).orElse(null);
            var user = userRepository.findById(assessmentRequestDTO.getUser_id()).orElse(null);
            var payment = payRepository.findById(assessmentRequestDTO.getPayment_id()).orElse(null);
            if (assessmentRequestDTO.getTotalAmount() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Please insert amount of request", "Failed", null));
            }
            if (service != null && user != null && payment != null) {
                AssessmentRequests asr = AssessmentRequests.builder()
                        .service_id(service)
                        .user_id(user)
                        .status(true)
                        .payment_id(payment)
                        .totalAmount(assessmentRequestDTO.getTotalAmount())
                        .build();
                var savedAsr = asrRepository.save(asr);
                var listAsrDetail = autoCreateAssessmentDetail(savedAsr);
                asrDetailRepository.saveAll(listAsrDetail);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Created", "Success", listAsrDetail));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Some fields are missing", "Failed", null));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> acceptRequest(int assessmentDetailRequestID) {
        try {
            var assessmentRequestDetail = asrDetailRepository.findById(assessmentDetailRequestID).orElse(null);
            assessmentRequestDetail.setCheckIn(true);
            var savedAsrDetail = asrDetailRepository.save(assessmentRequestDetail);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Changed", "Success", savedAsrDetail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> delegateRequest(int assessmentRequestDetailID, int user_id) {
        try {
            var assessmentRequestDetail = asrDetailRepository.findById(assessmentRequestDetailID).orElse(null);
            var user = userRepository.findById(user_id).orElse(null);
            if (!user.getRole_id().getRoleName().equals("Assessment staff")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("User is not Assessment staff", "Failed", null));
            }
            assessmentRequestDetail.setByAssessmentID(user);
            var savedAsrDetail = asrDetailRepository.save(assessmentRequestDetail);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Changed", "Success", savedAsrDetail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }


    @Override
    public ResponseEntity<ResponseObject> receiveDelegation(int assessmentRequestDetailID, int managerID) {
        try {
            var assessmentRequestDetail = asrDetailRepository.findById(assessmentRequestDetailID).orElse(null);
            var manager = userRepository.findById(managerID).orElse(null);
            if (assessmentRequestDetail == null || manager == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Assessment request detail or manager not found", "Failed", null));
            }
            if (!manager.getRole_id().getRoleName().equals("Manager")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("User is not a Manager", "Failed", null));
            }
            assessmentRequestDetail.setByAssessmentID(manager);
            var savedAsrDetail = asrDetailRepository.save(assessmentRequestDetail);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Delegation received", "Success", savedAsrDetail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }


    public List<AssessmentRequestsDetail> autoCreateAssessmentDetail(AssessmentRequests req) {
        List<AssessmentRequestsDetail> details = new ArrayList<>();
        int amount = req.getTotalAmount();
        while (amount > 0) {
            AssessmentRequestsDetail p = AssessmentRequestsDetail.builder()
                    .byAssessmentID(null)
                    .ARequestID(req)
                    .isDia(true)
                    .price(1000)
                    .sampleSize(10)
                    .isCheckIn(false)
                    .status(true)
                    .user_id(req.getUser_id())
                    .build();
            details.add(p);
            amount -= 1;
        }
        return details;
    }

    @Override
    public ResponseEntity<ResponseObject> searchRequest(searchRequestDTO searchDTO) {
        try {
            Specification<Assessment> spec = Specification.where(null);

            if (searchDTO.getAssessmentID() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("id"), searchDTO.getAssessmentID()));
            }
            List<Assessment> assessments = asRepository.findAll(spec);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Found", "Success", assessments));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> saveAssessment(inspectParameterDTO inspectParameterDTO) {
        try {
            // dua cac parameter vao trong database
            Assessment assessment = Assessment.builder()
                    .isDiamond(inspectParameterDTO.getIsDiamond())
                    .comments(inspectParameterDTO.getComments())
                    .depth(inspectParameterDTO.getDepth())
                    .tablee(inspectParameterDTO.getTablee())
                    .crowAngle(inspectParameterDTO.getCrowAngle())
                    .crowHeight(inspectParameterDTO.getCrowHeight())
                    .pavillionAngle(inspectParameterDTO.getPavillionAngle())
                    .pavillionDepth(inspectParameterDTO.getPavillionDepth())
                    .starLength(inspectParameterDTO.getStarLength())
                    .lowerHalf(inspectParameterDTO.getLowerHalf())
                    .girdle(inspectParameterDTO.getGirdle())
                    .caratWeight(inspectParameterDTO.getCaratWeight())
                    .colorGrade(inspectParameterDTO.getColorGrade())
                    .clarityGrade(inspectParameterDTO.getClarityGrade())
                    .cutGrade(inspectParameterDTO.getCutGrade())
                    .proportions(inspectParameterDTO.getProportions())
                    .polish(inspectParameterDTO.getPolish())
                    .symmetry(inspectParameterDTO.getSymmetry())
                    .flourescence(inspectParameterDTO.getFlourescence())
                    .measurememt(inspectParameterDTO.getMeasurememt())
                    .shapeAndCut(inspectParameterDTO.getShapeAndCut())
                    .dates(inspectParameterDTO.getDates())
                    .number(inspectParameterDTO.getNumber())
                    .build();

            Assessment savedAssessment = asRepository.save(assessment);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Created", "Success", savedAssessment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), "Failed", null));
        }
    }

    @Override
    public ByteArrayInputStream createPdf(createPdfDTO createPdfDTO) {
        Optional<Assessment> optionalAssessment = asRepository.findById(createPdfDTO.getAssessmentID());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        if (optionalAssessment.isPresent()) {
            Assessment assessment = optionalAssessment.get();


            try {
                com.itextpdf.text.Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                document.add(new Paragraph("Comments: " + assessment.getComments()));
                document.add(new Paragraph("Depth: " + assessment.getDepth()));
                document.add(new Paragraph("Table: " + assessment.getTablee()));
                document.add(new Paragraph("Crown Angle: " + assessment.getCrowAngle()));
                document.add(new Paragraph("Crown Height: " + assessment.getCrowHeight()));
                document.add(new Paragraph("Pavilion Angle: " + assessment.getPavillionAngle()));
                document.add(new Paragraph("Pavilion Depth: " + assessment.getPavillionDepth()));
                document.add(new Paragraph("Star Length: " + assessment.getStarLength()));
                document.add(new Paragraph("Lower Half: " + assessment.getLowerHalf()));
                document.add(new Paragraph("Girdle: " + assessment.getGirdle()));
                document.add(new Paragraph("Carat Weight: " + assessment.getCaratWeight()));
                document.add(new Paragraph("Color Grade: " + assessment.getColorGrade()));
                document.add(new Paragraph("Clarity Grade: " + assessment.getClarityGrade()));
                document.add(new Paragraph("Cut Grade: " + assessment.getCutGrade()));
                document.add(new Paragraph("Proportions: " + assessment.getProportions()));
                document.add(new Paragraph("Polish: " + assessment.getPolish()));
                document.add(new Paragraph("Symmetry: " + assessment.getSymmetry()));
                document.add(new Paragraph("Fluorescence: " + assessment.getFlourescence()));
                document.add(new Paragraph("Measurement: " + assessment.getMeasurememt()));
                document.add(new Paragraph("Shape and Cut: " + assessment.getShapeAndCut()));
                document.add(new Paragraph("Date: " + assessment.getDates()));
                document.add(new Paragraph("Number: " + assessment.getNumber()));

                document.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

}


