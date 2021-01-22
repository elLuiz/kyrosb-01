package com.example.kyros.api.model.utils.converter;

import com.example.kyros.api.model.response.ClientResponseModel;
import com.example.kyros.api.model.utils.formatter.CpfFormatter;
import com.example.kyros.api.model.utils.formatter.PhoneFormatter;
import com.example.kyros.api.model.utils.formatter.ResponseFormatter;
import com.example.kyros.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ModelMapperConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClientResponseModel convertToClientResponseModel(Client from){
        formatClientData(from);
        return modelMapper.map(from, ClientResponseModel.class);
    }

    private void formatClientData(Client client){
        ResponseFormatter cpfFormatter = new CpfFormatter();
        ResponseFormatter phoneFormatter = new PhoneFormatter();
        client.setCpf(cpfFormatter.formatInput(client.getCpf()));
        client.setPhone(phoneFormatter.formatInput(client.getPhone()));
    }
}
