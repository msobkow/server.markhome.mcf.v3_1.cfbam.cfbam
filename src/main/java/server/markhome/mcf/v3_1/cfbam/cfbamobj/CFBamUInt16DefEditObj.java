// Description: Java 25 edit object instance implementation for CFBam UInt16Def.

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

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamUInt16DefEditObj
	extends CFBamAtomEditObj

	implements ICFBamUInt16DefEditObj
{

	public CFBamUInt16DefEditObj( ICFBamUInt16DefObj argOrig ) {
		super( argOrig );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getUInt16DefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "UInt16Def" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( super.getObjScope() );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else if( container instanceof ICFBamSchemaDefObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public ICFBamValueObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamUInt16DefObj retobj = getSchema().getUInt16DefTableObj().realiseUInt16Def( (ICFBamUInt16DefObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsUInt16Def().forget();
	}

	@Override
	public ICFBamValueObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamValueObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamValueObj create() {
		copyRecToOrig();
		ICFBamUInt16DefObj retobj = ((ICFBamSchemaObj)getOrigAsUInt16Def().getSchema()).getUInt16DefTableObj().createUInt16Def( getOrigAsUInt16Def() );
		if( retobj == getOrigAsUInt16Def() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamValueEditObj update() {
		getSchema().getUInt16DefTableObj().updateUInt16Def( (ICFBamUInt16DefObj)this );
		return( null );
	}

	@Override
	public CFBamValueEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getUInt16DefTableObj().deleteUInt16Def( getOrigAsUInt16Def() );
		return( null );
	}

	@Override
	public ICFBamUInt16DefTableObj getUInt16DefTable() {
		return( orig.getSchema().getUInt16DefTableObj() );
	}

	@Override
	public ICFBamUInt16DefEditObj getEditAsUInt16Def() {
		return( (ICFBamUInt16DefEditObj)this );
	}

	@Override
	public ICFBamUInt16DefObj getOrigAsUInt16Def() {
		return( (ICFBamUInt16DefObj)orig );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			rec = getOrigAsUInt16Def().getSchema().getCFBamBackingStore().getFactoryUInt16Def().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamValue value ) {
		if( rec != value ) {
			super.setRec( value );
		}
	}

	@Override
	public ICFBamUInt16Def getUInt16DefRec() {
		return( (ICFBamUInt16Def)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
			requiredContainerScope = null;
			optionalLookupDefSchema = null;
			optionalChildrenRefTableCol = null;
			optionalChildrenRefIndexCol = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public Integer getOptionalInitValue() {
		return( getUInt16DefRec().getOptionalInitValue() );
	}

	@Override
	public void setOptionalInitValue( Integer value ) {
		if( getUInt16DefRec().getOptionalInitValue() != value ) {
			getUInt16DefRec().setOptionalInitValue( value );
		}
	}

	@Override
	public Integer getOptionalMinValue() {
		return( getUInt16DefRec().getOptionalMinValue() );
	}

	@Override
	public void setOptionalMinValue( Integer value ) {
		if( getUInt16DefRec().getOptionalMinValue() != value ) {
			getUInt16DefRec().setOptionalMinValue( value );
		}
	}

	@Override
	public Integer getOptionalMaxValue() {
		return( getUInt16DefRec().getOptionalMaxValue() );
	}

	@Override
	public void setOptionalMaxValue( Integer value ) {
		if( getUInt16DefRec().getOptionalMaxValue() != value ) {
			getUInt16DefRec().setOptionalMaxValue( value );
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamUInt16Def origRec = getOrigAsUInt16Def().getUInt16DefRec();
		ICFBamUInt16Def myRec = getUInt16DefRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamUInt16Def origRec = getOrigAsUInt16Def().getUInt16DefRec();
		ICFBamUInt16Def myRec = getUInt16DefRec();
		myRec.set( origRec );
	}
}
