package com.crossover.techtrial.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DailyElectricity class will hold sum, average,minimum and maximum electricity for a given day.
 * @author Crossover
 *
 */

public class DailyElectricity implements Serializable {
  
  private static final long serialVersionUID = 3605549122072628877L;

  private LocalDate date;
  
  private Long sum = (long) 0;
  
  private Double average;
  
  private Long min = (long) 0;
  
  private Long max = (long) 0;
  
  private int hrsreg = 0;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getSum() {
    return sum;
  }

  public void setSum(Long sum) {
    this.sum += sum;
    this.hrsreg += 1;
    this.average = (double) (this.sum/this.hrsreg);
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public Long getMin() {
    return min;
  }

  public void setMin(Long min) {
	  if(min<this.min)
    this.min = min;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(Long max) {
	  if(max>this.max)
    this.max = max;
  }

  @Override
  public String toString() {
    return "DailyElectricity [date=" + date + ", sum=" + sum + ", average="
        + average + ", min=" + min + ", max=" + max + "]";
  }

public int getHrsreg() {
	return hrsreg;
}

public void setHrsreg(int hrsreg) {
	this.hrsreg = hrsreg;
}

}
