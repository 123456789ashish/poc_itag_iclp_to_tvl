package com.batch.processor;

import com.batch.model.ICLPEntity;
import com.batch.model.ITAGEntity;
import com.batch.model.TVLFile.TVLPlateDetail;
import com.batch.model.TVLFile.TVLPlateDetails;
import com.batch.model.TVLFile.TVLTagDetail;
import com.batch.writer.ICLPRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TvlProcessor {


}

    /*

        implements ItemProcessor<ITAGEntity, TVLTagDetail> {

    @Autowired
    ICLPRepository iclpRepository;

    @Autowired
    EntityManager em;

    @Override
    public TVLTagDetail process(ITAGEntity itagEntity) {
        TVLTagDetail tvlTagDetail = new TVLTagDetail();

        tvlTagDetail.setTagAgencyID(itagEntity.getTAG_AGENCY_ID());
        tvlTagDetail.setTagSerialNumber(itagEntity.getTAG_SERIAL_NUMBER());
        tvlTagDetail.setTagStatus(itagEntity.getTAG_STATUS());
        tvlTagDetail.setTagAccountInfo(itagEntity.getTAG_ACCT_INFO());

        List<TVLPlateDetails> tVLPlateDetails = getTVLPlateList(findPlateList(itagEntity));

        TVLPlateDetail tvlPLateDetail=new TVLPlateDetail();
        tvlPLateDetail.setTVLPlateDetails(tVLPlateDetails);

        tvlTagDetail.settVLPlateDetail(tvlPLateDetail);

        //iclpRepository.findAll()

        return tvlTagDetail;
    }


    private List<TVLPlateDetails> getTVLPlateList(List<ICLPEntity> plateList) {

       return plateList.stream().map(p -> new TVLPlateDetails(
                p.getLIC_NUMBER(),
                p.getLIC_STATE(),
                p.getLIC_TYPE()))
                .collect(Collectors.toList());

    }


    List<ICLPEntity> findPlateList(ITAGEntity itagEntity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ICLPEntity> cq = cb.createQuery(ICLPEntity.class);

        Root<ICLPEntity> iclp = cq.from(ICLPEntity.class);

        Predicate tagAgencyID = cb.equal(iclp.get("TAG_AGENCY_ID"), itagEntity.getTAG_AGENCY_ID());
        Predicate tagSerialNum = cb.equal(iclp.get("TAG_SERIAL_NUMBER"), itagEntity.getTAG_SERIAL_NUMBER());

        cq.where(tagAgencyID, tagSerialNum);

        TypedQuery<ICLPEntity> query = em.createQuery(cq);
        return query.getResultList();
    }

}
*/