package com.tpe.service;


import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repo;

public class SmsService implements MessageService{

    private Repo repo;

    public SmsService(Repo repo) {
        this.repo = repo;
    }

    @Override
    public void sendMessage(Message message){
        System.out.println("Ben bir SMS servisiyim. Mesajınız: "+message.getMessage());
    }

    @Override
    public void saveMessage(Message message) {
        //repodaki save metodunu kullanmam gerekiyor??
        //Repo repo=new FileRepository();
        repo.save(message);
    }
}
