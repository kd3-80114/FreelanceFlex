package com.app.service.buyer;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Gigs;
import com.app.entities.Orders;
import com.app.dao.BuyerDao;
import com.app.dao.OrderDao;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public BuyerDTO findById(Long id) {
		
		return mapper.map(buyerDao.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Buyer with given id does not exist")),
				BuyerDTO.class) ;	
		}
	
	@Override
	public BuyerDTO addBuyer(BuyerDTO buyer) {
		
		try {
			Buyer buyerCreated = buyerDao.save(mapper.map(buyer, Buyer.class));
			return mapper.map(buyerCreated, BuyerDTO.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public PlaceOrderDTO createNewOrder(PlaceOrderDTO order) {
		// to get current time
		LocalDateTime currentTime = LocalDateTime.now();
		
		System.out.println(order.toString());
		Integer duration = order.getGigToOrder().getDeliveryTime();
		
		Orders newOrder = mapper.map(order,Orders.class);
		
		// as new order gigs and freelancer is not mapped so it is null
		// we will need to create them manually
		Freelancer newFreelancer = mapper.map(order.getGigToOrder().getFreelancer(),Freelancer.class);
		newOrder.setFreelancer(newFreelancer);
		
		Gigs newGig = mapper.map(order.getGigToOrder(),Gigs.class);
		newOrder.setGigs(newGig);
		
		Buyer buyer =	buyerDao.findById(order.getBuyer().getId())
						.orElseThrow(()-> new ResourceNotFoundException("Not Found"));
//		Buyer buyer = mapper.map(order.getBuyer(),Buyer.class);
//		buyer.setId(order.getBuyerOrder().getId());
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
//		newOrder.getBuyer().setId(order.getBuyer().getId());
//		newOrder.getBuyer().setEmail(order.getBuyer().getEmail());
		
		PlaceOrderDTO returnOrder = mapper.map(orderDao.save(newOrder),PlaceOrderDTO.class);
		returnOrder.setGigToOrder(order.getGigToOrder());
		return returnOrder;
	} 	
}
