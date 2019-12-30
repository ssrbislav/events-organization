package com.ftn.eventsorganization.DTO;

public class SectorDTO {

    private String sectorMark;

    private Long numOfRows;

    private Long numOfColumns;

    private Long hallId;

    public SectorDTO() {
    }

    public SectorDTO(String sectorMark, Long numOfRows, Long numOfColumns, Long hallId) {
        this.sectorMark = sectorMark;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.hallId = hallId;
    }

    public String getSectorMark() {
        return sectorMark;
    }

    public void setSectorMark(String sectorMark) {
        this.sectorMark = sectorMark;
    }

    public Long getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Long numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Long getNumOfColumns() {
        return numOfColumns;
    }

    public void setNumOfColumns(Long numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }
}
