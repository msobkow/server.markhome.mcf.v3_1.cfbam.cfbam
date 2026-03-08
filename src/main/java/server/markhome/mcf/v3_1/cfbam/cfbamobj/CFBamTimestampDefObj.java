// Description: Java 25 base object instance implementation for TimestampDef

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

public class CFBamTimestampDefObj
	extends CFBamAtomObj
	implements ICFBamTimestampDefObj
{

	public CFBamTimestampDefObj() {
		super();
	}

	public CFBamTimestampDefObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getTimestampDefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "TimestampDef" );
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
		ICFBamTimestampDefObj retobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().realiseTimestampDef(
			(ICFBamTimestampDefObj)this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().reallyDeepDisposeTimestampDef( (ICFBamTimestampDefObj)this );
	}

	@Override
	public ICFBamValueObj read() {
		ICFBamTimestampDefObj retobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().readTimestampDefByIdIdx( getPKey(), false );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj read( boolean forceRead ) {
		ICFBamTimestampDefObj retobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().readTimestampDefByIdIdx( getPKey(), forceRead );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveUp() {
		ICFBamTimestampDefObj retobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().moveUpTimestampDef( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveDown() {
		ICFBamTimestampDefObj retobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().moveDownTimestampDef( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamTimestampDefTableObj getTimestampDefTable() {
		return( ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj() );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryTimestampDef().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableTimestampDef().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamValue value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamTimestampDef ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamTimestampDefRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFBamTimestampDef getTimestampDefRec() {
		return( (ICFBamTimestampDef)getRec() );
	}

	@Override
	public ICFBamValueEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamTimestampDefObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamTimestampDefObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().lockTimestampDef( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getTimestampDefTableObj().newEditInstance( lockobj );
		return( (ICFBamValueEditObj)edit );
	}

	@Override
	public ICFBamTimestampDefEditObj getEditAsTimestampDef() {
		return( (ICFBamTimestampDefEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamValue rec = getRec();
			createdBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getCreatedByUserId() );
		}
		return( createdBy );
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( getRec().getCreatedAt() );
	}

	@Override
	public ICFSecSecUserObj getUpdatedBy() {
		if( updatedBy == null ) {
			ICFBamValue rec = getRec();
			updatedBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public String getOptionalDummy() {
		return( getTimestampDefRec().getOptionalDummy() );
	}
}
