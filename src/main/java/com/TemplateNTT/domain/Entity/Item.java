package com.TemplateNTT.domain.Entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
	@Id
	private String itemId;
	
	@NotBlank
	@Size(max = 100)
	private String itemName;
	
	@Size(max = 100)
	private String frgnName;
	
	private Integer itmsGrpCod;
	
	@Size(max = 2)
	@Pattern(regexp = "AC|PA", message = "Business Partner type not exist the valid values be: AC= Active, PA - Pasive")
	private String itemType;
	
	@Size( max = 100)
	private String codeBars;
	
	private Boolean valid;
	
	private Boolean prchseItem;
	
	private Boolean sellItem;
	
	private Boolean invntItem;
	
	private String bs;

}
