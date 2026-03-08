// Description: Java 25 interface for a Chain record implementation

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
//import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamChain
{
        public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
        public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
        public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
        public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_ID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 ID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_ID_INIT_VALUE );
	public static final String S_DEFSCHEMAID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 DEFSCHEMAID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_DEFSCHEMAID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public static final String S_TABLEID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 TABLEID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_TABLEID_INIT_VALUE );
	public static final String S_PREVRELATIONID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 PREVRELATIONID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_PREVRELATIONID_INIT_VALUE );
	public static final String S_NEXTRELATIONID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 NEXTRELATIONID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_NEXTRELATIONID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa80f;
	public final static String S_CLASS_CODE = "a80f";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();
	public void setCreatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getCreatedAt();
	public void setCreatedAt( LocalDateTime value );
	public CFLibDbKeyHash256 getUpdatedByUserId();
	public void setUpdatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getUpdatedAt();
	public void setUpdatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getPKey();
	public void setPKey(CFLibDbKeyHash256 requiredId);
	
	public CFLibDbKeyHash256 getRequiredId();
	public void setRequiredId( CFLibDbKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public ICFBamTable getRequiredContainerTable();
	public ICFBamSchemaDef getOptionalLookupDefSchema();
	public ICFBamRelation getRequiredLookupPrevRel();
	public ICFBamRelation getRequiredLookupNextRel();
	public void setRequiredContainerTable(ICFBamTable argObj);
	public void setRequiredContainerTable(CFLibDbKeyHash256 argTableId);
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj);
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId);
	public void setRequiredLookupPrevRel(ICFBamRelation argObj);
	public void setRequiredLookupPrevRel(CFLibDbKeyHash256 argPrevRelationId);
	public void setRequiredLookupNextRel(ICFBamRelation argObj);
	public void setRequiredLookupNextRel(CFLibDbKeyHash256 argNextRelationId);
	public CFLibDbKeyHash256 getOptionalDefSchemaId();
	public String getRequiredName();
	public void setRequiredName( String value );
	public String getOptionalShortName();
	public void setOptionalShortName( String value );
	public String getOptionalLabel();
	public void setOptionalLabel( String value );
	public String getOptionalShortDescription();
	public void setOptionalShortDescription( String value );
	public String getOptionalDescription();
	public void setOptionalDescription( String value );
	public CFLibDbKeyHash256 getRequiredTableId();
	public String getOptionalSuffix();
	public void setOptionalSuffix( String value );
	public CFLibDbKeyHash256 getRequiredPrevRelationId();
	public CFLibDbKeyHash256 getRequiredNextRelationId();
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamChain src );
	public void setChain( ICFBamChain src );
	public void set( ICFBamChainH src );
	public void setChain( ICFBamChainH src );

	public String getXmlAttrFragment();

	@Override
	public String toString();
}
