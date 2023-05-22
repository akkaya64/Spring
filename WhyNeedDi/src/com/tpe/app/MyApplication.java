package com.tpe.app;
import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repo;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SmsService;

public class MyApplication {
    public static void main(String[] args) {

        Message message=new Message();
        message.setMessage("Siparişiniz kargoya verildi.");

        //mesajı maille kullanıcıya gönder
//        MailService mailService=new MailService();
//        mailService.sendMessage(message);

        //vazgeçtik, hala sms kullanan var:)
//        SmsService smsService=new SmsService();
//        smsService.sendMessage(message);

        //bu böyle gitmez..interface çözüm olablir mi??
//        MessageService service=new MailService();//veya diğeri
//        service.sendMessage(message);


        //run timeda servisi belirlemek istersek
//        MessageService service=null;
//        String serviceName="MailService";
//
//        if(args.length>0){
//            serviceName=args[0];
//        }
//
//        if (serviceName.equalsIgnoreCase("MailService")){
//            service=new MailService();
//        } else if (serviceName.equalsIgnoreCase("SmsService")) {
//            service=new SmsService();
//        }
//        service.sendMessage(message);

        //mesajları kalıcı hale getirmek istersek??
        Repo repo=new FileRepository();
        MessageService service=new MailService(repo);//veya diğeri
        service.sendMessage(message);
        service.saveMessage(message);//dosyaya kaydetmek istersek:servise gidip const. değiştirmek zorundayız
        //bakım ve geliştirme zor

        //ancak hala new leme işlemi yapıyoruz....
        MessageService service2=new SmsService(repo);
        service2.saveMessage(message);


        //loosely coupling için: 1-interface,2-DI
        //bakım, geliştirme kolaylaştı, classlar arası bağımlılık azaldı, maliyet de kısmen azaldı.



    }
}
