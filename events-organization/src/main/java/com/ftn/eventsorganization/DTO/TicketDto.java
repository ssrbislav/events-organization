package com.ftn.eventsorganization.DTO;

public class TicketDto {
	private Long eventId;
	private String sectorMark;
	private int row;
	private int column;

	public TicketDto() {

	}

	public TicketDto(Long eventId, String sectorMark, int row, int column) {
		this();
		this.eventId = eventId;
		this.sectorMark = sectorMark;
		this.row = row;
		this.column = column;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getSectorMark() {
		return sectorMark;
	}

	public void setSectorMark(String sectorMark) {
		this.sectorMark = sectorMark;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
