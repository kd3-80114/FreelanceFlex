package com.app.service.buyer;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.PaymentDTO;
import com.app.dto.ReviewsDTO;
import com.app.dto.SignInDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.dto.freelancerdto.FreelancerDTO;

import com.app.entities.Address;
import com.app.entities.ApiResponse;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Reviews;
import com.app.entities.RoleType;
import com.app.entities.SignIn;
import com.app.dao.BuyerDao;
import com.app.dao.FreelancerDao;
import com.app.dao.GigDao;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Gigs;
import com.app.entities.Orders;
import com.app.entities.Payment;
import com.app.dao.BuyerDao;
import com.app.dao.OrderDao;
import com.app.dao.PaymentDao;
import com.app.dao.ReviewDao;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private FreelancerDao freelancerDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private GigDao gigDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public BuyerDTO findById(Long id) {

		return mapper.map(buyerDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Buyer with given id does not exist")),
				BuyerDTO.class);
	}

	@Override
	public BuyerDTO addBuyer(BuyerDTO buyer) {

		SignInDTO signInDto = new SignInDTO();
		signInDto.setEmail(buyer.getEmail());
		String encryptedPassword = passwordEncoder.encode(buyer.getPassword());
		signInDto.setPassword(encryptedPassword);
		signInDto.setUserRole(RoleType.ROLE_BUYER);
		buyer.setSignIn(signInDto);

		System.out.println(encryptedPassword);
		buyer.setPassword(encryptedPassword);
		System.out.println(buyer.getPassword());

		try {
			Buyer buyerCreated = buyerDao.save(mapper.map(buyer, Buyer.class));
			return mapper.map(buyerCreated, BuyerDTO.class);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer) {
		// TODO Auto-generated method stub

		Buyer updatedBuyer = buyerDao.findById(buyerId)
				.orElseThrow(() -> new ResourceNotFoundException("Buyer with given id does not exist"));
		updatedBuyer.setFirstName(buyer.getFirstName());
		updatedBuyer.setLastName(buyer.getLastName());
		updatedBuyer.setEmail(buyer.getEmail());
		updatedBuyer.setContactNo(buyer.getContactNo());
		updatedBuyer.setDescription(buyer.getDescription());
		updatedBuyer.setProfilePicture(buyer.getProfilePicture());
		// Address updation
		Address address = updatedBuyer.getPermanentAddress(); //
		address.setCity(buyer.getPermanentAddress().getCity());

		address.setCountry(buyer.getPermanentAddress().getCountry());

		address.setLandmark(buyer.getPermanentAddress().getLandmark());

		address.setPincode(buyer.getPermanentAddress().getPincode());

		address.setState(buyer.getPermanentAddress().getState());

		return mapper.map(updatedBuyer, BuyerDTO.class);
	}

	@Override
	public ReviewsDTO addReview(Long freelanceId, Long buyerId, ReviewsDTO review) {
		try {
			// freelancerId
			Freelancer freelancer = freelancerDao.findById(freelanceId)
					.orElseThrow(() -> new ResourceNotFoundException("Freelancer with given id does not exist"));
			// buyerId
			Buyer buyer = buyerDao.findById(buyerId)
					.orElseThrow(() -> new ResourceNotFoundException("Buyer with id not found"));

			review.setFreelancer(freelancer);
			review.setBuyer(buyer);
			Reviews reviewCreated = reviewDao.save(mapper.map(review, Reviews.class));

			System.out.println(reviewCreated);
			return mapper.map(reviewCreated, ReviewsDTO.class);

		} catch (Exception e) {
			System.out.println("before null");
			return null;
		}
	}

	@Override
	public PaymentDTO addPayment(Long freelanceId, Long buyerId, PaymentDTO payment) {
		// TODO Auto-generated method stub
		try {

			// freelancerId
			Freelancer freelancer = freelancerDao.findById(freelanceId)
					.orElseThrow(() -> new ResourceNotFoundException("Freelancer with given id does not exist"));
			System.out.println(freelancer.getId());
			// buyerId
			Buyer buyer = buyerDao.findById(buyerId)
					.orElseThrow(() -> new ResourceNotFoundException("Buyer with id not found"));

			payment.setBuyer(buyer);
			payment.setFreelancer(freelancer);

			Payment paymentAdded = paymentDao.save(mapper.map(payment, Payment.class));
			System.out.println(paymentAdded);
			return mapper.map(paymentAdded, PaymentDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("before null");
			return null;
		}
	}

	@Override
	public PlaceOrderDTO createNewOrder(PlaceOrderDTO order) {
		// to get current time
		LocalDateTime currentTime = LocalDateTime.now();

		System.out.println(order.toString());
		Integer duration = order.getGigToOrder().getDeliveryTime();

		Orders newOrder = mapper.map(order, Orders.class);

		// as new order gigs and freelancer is not mapped so it is null
		// we will need to create them manually
		Freelancer newFreelancer = mapper.map(order.getGigToOrder().getFreelancer(), Freelancer.class);
		newOrder.setFreelancer(newFreelancer);

		Gigs newGig = mapper.map(order.getGigToOrder(), Gigs.class);
		newOrder.setGigs(newGig);

		Buyer buyer = buyerDao.findById(order.getBuyer().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		// Buyer buyer = mapper.map(order.getBuyer(),Buyer.class);
		// buyer.setId(order.getBuyerOrder().getId());
		// setting buyer information in new order from above PlaceOrderDTO order
		newOrder.setBuyer(buyer);
		System.out.println(newOrder.getBuyer().toString());

		// setting Gigs information in new order from above PlaceOrderDTO order
		newOrder.setAmount(order.getGigToOrder().getPrice());
		newOrder.setStartDate(currentTime);
		newOrder.setDeliveryDate(currentTime.plusDays(duration));

		// setting freelancer information in new order from above PlaceOrderDTO order
		newOrder.getGigs().setId(order.getGigToOrder().getId());
		newOrder.getFreelancer().setId(order.getGigToOrder().getFreelancer().getId());
		// newOrder.getBuyer().setId(order.getBuyer().getId());
		// newOrder.getBuyer().setEmail(order.getBuyer().getEmail());

		PlaceOrderDTO returnOrder = mapper.map(orderDao.save(newOrder), PlaceOrderDTO.class);
		returnOrder.setGigToOrder(order.getGigToOrder());
		return returnOrder;
	}

	public List<ReviewsDTO> getAllReviews(Long buyerId) {
		// Assuming you have a method in reviewDao to retrieve reviews by buyerId
		List<Reviews> reviews = reviewDao.findByBuyerId(buyerId);
		// Mapping Reviews objects to ReviewsDTO
		List<ReviewsDTO> reviewsDTOList = reviews.stream()
				.map(review -> mapper.map(review, ReviewsDTO.class))
				.collect(Collectors.toList());

		return reviewsDTOList;
	}

	@Override
	public ApiResponse uploadImage(Long id, MultipartFile image) throws IOException {
		Buyer buyer = buyerDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("buyer with given id does not exist")); // Exception
																											// message
																											// modified
		buyer.setProfilePicture(image.getBytes());
		buyerDao.save(buyer);
		return new ApiResponse("Image file uploaded successfully for buyerid " + id);
	}

	@Override
	public byte[] serveImageOfbuyer(long id) {

		Buyer buyer = buyerDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));

		if (buyer != null) {

			return buyer.getProfilePicture();
		} else
			throw new ApiException("Image not yet assigned !!!!");
	}

	@Override
	public List<PaymentDTO> getAllPayments(Long buyerId) {
		// Assuming you have a method in paymentDao to retrieve reviews by buyerId
		List<Payment> payments = paymentDao.findByBuyerId(buyerId);
		// Mapping payments objects to PaymentsDTO
		List<PaymentDTO> paymentsDTOList = payments.stream()
				.map(payment -> mapper.map(payment, PaymentDTO.class))
				.collect(Collectors.toList());

		return paymentsDTOList;
	}

	@Override
	public List<Orders> getOrderDetails(Long buyerId) {
		// NOT WORKING PROPERLY ------------------------------
		// List<Orders> orderList =orderDao.findOrdersByBuyerIdNative(buyerId);

		// List<Orders> finalOrderList = new ArrayList<>();
		// List<Orders> orderList = orderDao.findAll();
		List<Orders> finalOrderList = orderDao.findAllOrderByBuyerId(buyerId);
		System.out.println(finalOrderList);

		return finalOrderList;
	}

	@Override
	public List<Gigs> getAllGigs(Long freelancerId) {
		List<Gigs> listOfGigs = gigDao.findByFreelancerId(freelancerId);
		return listOfGigs;
	}

	@Override
	public List<Gigs> getEveryGigs() {
		List<Gigs> allGigs= gigDao.findAll();
		
		return allGigs;
	}

	@Override
	public Long findFreelancerById(Long gigsId) {
		Long fid =gigDao.findFreelancerById(gigsId);
		System.out.println("fid==="+fid);
		return fid;
	}

	@Override
	public List<Long> findFreelancerByBuyerId(Long buyerId) {
		List<Long> fid =reviewDao.findFreelancerIdsByBuyerId(buyerId);
		System.out.println("fid==="+fid.size());
		return fid;
	}

	@Override
	public Long findFreelancerByOrderId(Long orderId) {
		Freelancer freelancer  = orderDao.findFreelancerByOrderId(orderId).get();
		System.out.println(freelancer.getId());
		return freelancer.getId();
	}
	
	
	
}
