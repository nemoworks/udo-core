package info.nemoworks.udo.service;

//import info.nemoworks.udo.model.SyncEvent;

//@Component
//public class SyncEventHandler {
//    @Autowired
//    UdoService udoService;
//
//    @Subscribe
//    public void syncEvent(UdoEvent syncEvent) {
//        System.out.println("eync event handler");
//        System.out.println(syncEvent);
//        Udo udo = (Udo) syncEvent.getSource();
//        JsonElement udoData = udo.getData();
//        Udo udo1 = udoService.getUdoById(udo.getId());
//        udo1.setData(udoData);
//        try {
//            udoService.saveOrUpdateUdo(udo1);
//        } catch (UdoServiceException e) {
//            e.printStackTrace();
//        }
//    }


//}
