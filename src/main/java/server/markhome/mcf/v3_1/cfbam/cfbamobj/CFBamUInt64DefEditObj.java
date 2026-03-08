// Description: Java 25 edit object instance implementation for CFBam UInt64Def.

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

public class CFBamUInt64DefEditObj
	extends CFBamAtomEditObj

	implements ICFBamUInt64DefEditObj
{

	public CFBamUInt64DefEditObj( ICFBamUInt64DefObj argOrig ) {
		super( argOrig );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getUInt64DefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "UInt64Def" );
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
		ICFBamUInt64DefObj retobj = getSchema().getUInt64DefTableObj().realiseUInt64Def( (ICFBamUInt64DefObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsUInt64Def().forget();
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
		ICFBamUInt64DefObj retobj = ((ICFBamSchemaObj)getOrigAsUInt64Def().getSchema()).getUInt64DefTableObj().createUInt64Def( getOrigAsUInt64Def() );
		if( retobj == getOrigAsUInt64Def() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamValueEditObj update() {
		getSchema().getUInt64DefTableObj().updateUInt64Def( (ICFBamUInt64DefObj)this );
		return( null );
	}

	@Override
	public CFBamValueEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getUInt64DefTableObj().deleteUInt64Def( getOrigAsUInt64Def() );
		return( null );
	}

	@Override
	public ICFBamUInt64DefTableObj getUInt64DefTable() {
		return( orig.getSchema().getUInt64DefTableObj() );
	}

	@Override
	public ICFBamUInt64DefEditObj getEditAsUInt64Def() {
		return( (ICFBamUInt64DefEditObj)this );
	}

	@Override
	public ICFBamUInt64DefObj getOrigAsUInt64Def() {
		return( (ICFBamUInt64DefObj)orig );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			rec = getOrigAsUInt64Def().getSchema().getCFBamBackingStore().getFactoryUInt64Def().newRec();
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
	public ICFBamUInt64Def getUInt64DefRec() {
		return( (ICFBamUInt64Def)getRec() );
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
	public BigDecimal getOptionalInitValue() {
		return( getUInt64DefRec().getOptionalInitValue() );
	}

	@Override
	public void setOptionalInitValue( BigDecimal value ) {
		if( getUInt64DefRec().getOptionalInitValue() != value ) {
			getUInt64DefRec().setOptionalInitValue( value );
		}
	}

	@Override
	public BigDecimal getOptionalMinValue() {
		return( getUInt64DefRec().getOptionalMinValue() );
	}

	@Override
	public void setOptionalMinValue( BigDecimal value ) {
		if( getUInt64DefRec().getOptionalMinValue() != value ) {
			getUInt64DefRec().setOptionalMinValue( value );
		}
	}

	@Override
	public BigDecimal getOptionalMaxValue() {
		return( getUInt64DefRec().getOptionalMaxValue() );
	}

	@Override
	public void setOptionalMaxValue( BigDecimal value ) {
		if( getUInt64DefRec().getOptionalMaxValue() != value ) {
			getUInt64DefRec().setOptionalMaxValue( value );
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamUInt64Def origRec = getOrigAsUInt64Def().getUInt64DefRec();
		ICFBamUInt64Def myRec = getUInt64DefRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamUInt64Def origRec = getOrigAsUInt64Def().getUInt64DefRec();
		ICFBamUInt64Def myRec = getUInt64DefRec();
		myRec.set( origRec );
	}
}
