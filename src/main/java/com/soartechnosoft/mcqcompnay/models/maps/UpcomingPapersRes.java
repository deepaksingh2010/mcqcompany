package com.soartechnosoft.mcqcompnay.models.maps;


import java.util.List;

import lombok.Data;

@Data
public class UpcomingPapersRes {
    private List<UpcomingPapers> lsUpcomingPapers;

    public List<UpcomingPapers> getLsUpcomingPapers(){
        return lsUpcomingPapers;
    }
    public void setLsUpcomingPapers(List<UpcomingPapers> lsUpcomingPapers){
        this.lsUpcomingPapers=lsUpcomingPapers;
    }
}