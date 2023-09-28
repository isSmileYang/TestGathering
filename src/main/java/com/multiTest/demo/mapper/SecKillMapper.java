package com.multiTest.demo.mapper;

import com.multiTest.demo.model.Entity.Masks;
import com.multiTest.demo.model.Entity.Purchaser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecKillMapper{

    List<Purchaser> getAllUser();

    Purchaser getUserById(Integer id);

    List<Masks> getAllProduct();

    Masks getProductById(Integer id);

    boolean updatePessLockInMySQL(Masks product);

    boolean updatePosiLockInMySQL(Masks product);

    boolean insertRecord(Masks record);

    boolean updateByAsynPattern(Masks product);
}
