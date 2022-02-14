package com.java_demos.taco_cloud.repository.jdbc_impl;

//@Repository
public class JdbcOrderRepository
//        implements OrderRepository
        {
//
//    private SimpleJdbcInsert orderInserter;
//    private SimpleJdbcInsert orderTacoInserter;
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    public JdbcOrderRepository(JdbcTemplate jdbc) {
//
//        this.orderInserter = new SimpleJdbcInsert(jdbc)
//                .withTableName("Taco_Order")
//                .usingGeneratedKeyColumns("id");
//
//        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
//                .withTableName("Taco_Order_Tacos");
//
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public Order save(Order order) {
//        order.setPlacedAt(new Date());
//        long orderId = saveOrderDetails(order);
//        order.setId(orderId);
//        List<Taco> tacos = order.getTacos();
//
//        for (Taco taco : tacos) {
//            saveTacoToOrder(taco, orderId);
//        }
//        return order;
//    }
//
//    private long saveOrderDetails(Order order) {
//        @SuppressWarnings("unchecked") Map<String, Object> values = objectMapper.convertValue(order, Map.class);
//        values.put("placedAt", order.getPlacedAt());
//        long orderId = orderInserter.executeAndReturnKey(values).longValue();
//        return orderId;
//    }
//
//    private void saveTacoToOrder(Taco taco, long orderId) {
//        Map<String, Object> values = new HashMap<>();
//        values.put("tacoOrder", orderId);
//        values.put("taco", taco.getId());
//        orderTacoInserter.execute(values);
//    }
}
