// Description: Java 25 base object instance implementation for DoubleDef

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public class CFBamDoubleDefObj
	extends CFBamAtomObj
	implements ICFBamDoubleDefObj
{

	public CFBamDoubleDefObj() {
		super();
	}

	public CFBamDoubleDefObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getDoubleDefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "DoubleDef" );
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
		ICFBamDoubleDefObj retobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().realiseDoubleDef(
			(ICFBamDoubleDefObj)this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().reallyDeepDisposeDoubleDef( (ICFBamDoubleDefObj)this );
	}

	@Override
	public ICFBamValueObj read() {
		ICFBamDoubleDefObj retobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().readDoubleDefByIdIdx( getPKey(), false );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj read( boolean forceRead ) {
		ICFBamDoubleDefObj retobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().readDoubleDefByIdIdx( getPKey(), forceRead );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveUp() {
		ICFBamDoubleDefObj retobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().moveUpDoubleDef( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveDown() {
		ICFBamDoubleDefObj retobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().moveDownDoubleDef( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamDoubleDefTableObj getDoubleDefTable() {
		return( ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj() );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryDoubleDef().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableDoubleDef().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamDoubleDef ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamDoubleDefRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFBamDoubleDef getDoubleDefRec() {
		return( (ICFBamDoubleDef)getRec() );
	}

	@Override
	public ICFBamValueEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamDoubleDefObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamDoubleDefObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().lockDoubleDef( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getDoubleDefTableObj().newEditInstance( lockobj );
		return( (ICFBamValueEditObj)edit );
	}

	@Override
	public ICFBamDoubleDefEditObj getEditAsDoubleDef() {
		return( (ICFBamDoubleDefEditObj)edit );
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
	public Double getOptionalInitValue() {
		return( getDoubleDefRec().getOptionalInitValue() );
	}

	@Override
	public Double getOptionalMinValue() {
		return( getDoubleDefRec().getOptionalMinValue() );
	}

	@Override
	public Double getOptionalMaxValue() {
		return( getDoubleDefRec().getOptionalMaxValue() );
	}
}
