// Description: Java 25 interface for a RelationCol record implementation

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

public interface ICFBamRelationCol
{
        public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
        public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
        public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
        public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_RELATIONID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 RELATIONID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_RELATIONID_INIT_VALUE );
	public static final String S_ID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 ID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_ID_INIT_VALUE );
	public static final String S_DEFSCHEMAID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 DEFSCHEMAID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_DEFSCHEMAID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public static final String S_FROMCOLID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 FROMCOLID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_FROMCOLID_INIT_VALUE );
	public static final String S_TOCOLID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 TOCOLID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_TOCOLID_INIT_VALUE );
	public static final String S_PREVID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 PREVID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_PREVID_INIT_VALUE );
	public static final String S_NEXTID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 NEXTID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_NEXTID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa836;
	public final static String S_CLASS_CODE = "a836";

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

	public ICFBamRelation getRequiredContainerRelation();
	public ICFBamSchemaDef getOptionalLookupDefSchema();
	public ICFBamRelationCol getOptionalLookupPrev();
	public ICFBamRelationCol getOptionalLookupNext();
	public ICFBamIndexCol getRequiredLookupFromCol();
	public ICFBamIndexCol getRequiredLookupToCol();
	public void setRequiredContainerRelation(ICFBamRelation argObj);
	public void setRequiredContainerRelation(CFLibDbKeyHash256 argRelationId);
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj);
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId);
	public void setOptionalLookupPrev(ICFBamRelationCol argObj);
	public void setOptionalLookupPrev(CFLibDbKeyHash256 argPrevId);
	public void setOptionalLookupNext(ICFBamRelationCol argObj);
	public void setOptionalLookupNext(CFLibDbKeyHash256 argNextId);
	public void setRequiredLookupFromCol(ICFBamIndexCol argObj);
	public void setRequiredLookupFromCol(CFLibDbKeyHash256 argFromColId);
	public void setRequiredLookupToCol(ICFBamIndexCol argObj);
	public void setRequiredLookupToCol(CFLibDbKeyHash256 argToColId);
	public CFLibDbKeyHash256 getRequiredRelationId();
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
	public CFLibDbKeyHash256 getRequiredFromColId();
	public CFLibDbKeyHash256 getRequiredToColId();
	public CFLibDbKeyHash256 getOptionalPrevId();
	public CFLibDbKeyHash256 getOptionalNextId();
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamRelationCol src );
	public void setRelationCol( ICFBamRelationCol src );
	public void set( ICFBamRelationColH src );
	public void setRelationCol( ICFBamRelationColH src );

	public String getXmlAttrFragment();

	@Override
	public String toString();
}
