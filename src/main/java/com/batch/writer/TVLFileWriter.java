package com.batch.writer;

import com.batch.model.TVLEntity;
import com.batch.model.TVLFile.TVLPlateDetail;
import com.batch.model.TVLFile.TVLPlateDetails;
import com.batch.model.TVLFile.TVLTagDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import org.codehaus.stax2.XMLStreamWriter2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TVLFileWriter implements ItemWriter<String> {

    @Autowired
    private TvlXmlGenerator tvlXmlGenerator;

    @Autowired
    EntityManager em;


    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {

        List<String> tagList = new ArrayList<String>();
        chunk.forEach(x -> tagList.add(x));

        List<TVLEntity> tvlDataList = getTvlDetailsList(tagList);

        List<TVLTagDetail> tvlTagDetailsList = getTvlDetailsMap(tvlDataList);


        XMLStreamWriter2 xmlStreamWriter = tvlXmlGenerator.getXmlWriter();

        JAXBContext jaxbContext= tvlXmlGenerator.getJaxbContext();

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter sw = tvlXmlGenerator.getSw();


        for (TVLTagDetail tvlTagDetails : tvlTagDetailsList) {
            try {

                jaxbMarshaller.marshal(tvlTagDetails, sw);
                String xmlString = sw.toString();

                xmlStreamWriter.writeRaw(xmlString);


            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            } catch (PropertyException e) {
                throw new RuntimeException(e);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        // flush every end of chunk
        xmlStreamWriter.flush();


    }


    private ArrayList<TVLTagDetail> getTvlDetailsMap(List<TVLEntity> tvlDataList) {

        HashMap<String, TVLTagDetail> map = new HashMap<>();

        tvlDataList.stream().forEach(x -> {
            String itagKey = x.getTAG_SERIAL_NUMBER();
            if (map.containsKey(itagKey)) {

                TVLTagDetail tvlTagDetail = map.get(itagKey);

                TVLPlateDetails tvlPlateDetails = new TVLPlateDetails();
                tvlPlateDetails.setPlateState(x.getLIC_STATE());
                tvlPlateDetails.setPlateNumber(x.getLIC_NUMBER());
                tvlPlateDetails.setPlateType(x.getLIC_TYPE());

                tvlTagDetail.gettVLPlateDetail().getTVLPlateDetails().add(tvlPlateDetails);

                map.put(itagKey, tvlTagDetail);

            } else {
                TVLTagDetail tagDetail = new TVLTagDetail();
                tagDetail.setTagStatus(x.getTAG_STATUS());
                tagDetail.setTagAgencyID(x.getTAG_AGENCY_ID());
                tagDetail.setTagSerialNumber(itagKey);
                tagDetail.setTagAccountInfo(x.getTAG_ACCT_INFO());

                TVLPlateDetail tvlPlateDetail = new TVLPlateDetail();
                tvlPlateDetail.setTVLPlateDetails(new ArrayList<TVLPlateDetails>());

                TVLPlateDetails tvlPlateDetails = new TVLPlateDetails();
                tvlPlateDetails.setPlateState(x.getLIC_STATE());
                tvlPlateDetails.setPlateNumber(x.getLIC_NUMBER());
                tvlPlateDetails.setPlateType(x.getLIC_TYPE());

                tvlPlateDetail.getTVLPlateDetails().add(tvlPlateDetails);

                tagDetail.settVLPlateDetail(tvlPlateDetail);

                map.put(itagKey, tagDetail);
            }

        });

        return new ArrayList<TVLTagDetail>(map.values());
    }


    List<TVLEntity> getTvlDetailsList(List<String> tagList) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TVLEntity> cq = cb.createQuery(TVLEntity.class);

        Root<TVLEntity> tvlDetails = cq.from(TVLEntity.class);

        cq.select(tvlDetails)
                .where(tvlDetails.get("TAG_SERIAL_NUMBER").in(tagList));

        TypedQuery<TVLEntity> query = em.createQuery(cq);
        return query.getResultList();
    }


    /*
    @Bean
    public StaxEventItemWriter<TVLEntity> customerItemWriter() throws Exception{
        String customerOutputPath = "C:\\Users\\ashis\\data\\ofc_docs\\BRISKWIN\\project_2024\\workspace\\poc_itag_iclp_to_tvl\\src\\main\\resources\\BTVL_1.xml";

       Map<String, Class> aliases = new HashMap<>();
        aliases.put("TVLTagDetail", TVLTagDetail.class);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliases);

        // StAX and Marshaller for serializing object to XML.
        StaxEventItemWriter<TVLTagDetail> writer = new StaxEventItemWriter<>();
        writer.setRootTagName("TagValidationList");
        writer.setMarshaller(marshaller);
        writer.setResource(new FileSystemResource(customerOutputPath));
        writer.afterPropertiesSet();

        return writer;



    }

    */


}