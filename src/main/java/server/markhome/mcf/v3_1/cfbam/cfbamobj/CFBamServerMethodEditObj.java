// Description: Java 25 edit object instance implementation for CFBam ServerMethod.

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

public class CFBamServerMethodEditObj
	extends CFBamScopeEditObj

	implements ICFBamServerMethodEditObj
{
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamTableObj requiredContainerForTable;
	protected List<ICFBamParamObj> optionalComponentsParams;

	public CFBamServerMethodEditObj( ICFBamServerMethodObj argOrig ) {
		super( argOrig );
		optionalLookupDefSchema = null;
		requiredContainerForTable = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getServerMethodTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ServerMethod" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerForTable();
		return( scope );
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
	public ICFBamScopeObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamServerMethodObj retobj = getSchema().getServerMethodTableObj().realiseServerMethod( (ICFBamServerMethodObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsServerMethod().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamServerMethodObj retobj = ((ICFBamSchemaObj)getOrigAsServerMethod().getSchema()).getServerMethodTableObj().createServerMethod( getOrigAsServerMethod() );
		if( retobj == getOrigAsServerMethod() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getServerMethodTableObj().updateServerMethod( (ICFBamServerMethodObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getServerMethodTableObj().deleteServerMethod( getOrigAsServerMethod() );
		return( null );
	}

	@Override
	public ICFBamServerMethodTableObj getServerMethodTable() {
		return( orig.getSchema().getServerMethodTableObj() );
	}

	@Override
	public ICFBamServerMethodEditObj getEditAsServerMethod() {
		return( (ICFBamServerMethodEditObj)this );
	}

	@Override
	public ICFBamServerMethodObj getOrigAsServerMethod() {
		return( (ICFBamServerMethodObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsServerMethod().getSchema().getCFBamBackingStore().getFactoryServerMethod().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			optionalLookupDefSchema = null;
			requiredContainerForTable = null;
		}
	}

	@Override
	public ICFBamServerMethod getServerMethodRec() {
		return( (ICFBamServerMethod)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getServerMethodRec().getRequiredTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getServerMethodRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getServerMethodRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getServerMethodRec().getRequiredName() != value ) {
			getServerMethodRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getServerMethodRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getServerMethodRec().getOptionalShortName() != value ) {
			getServerMethodRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getServerMethodRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getServerMethodRec().getOptionalLabel() != value ) {
			getServerMethodRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getServerMethodRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getServerMethodRec().getOptionalShortDescription() != value ) {
			getServerMethodRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getServerMethodRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getServerMethodRec().getOptionalDescription() != value ) {
			getServerMethodRec().setOptionalDescription( value );
		}
	}

	@Override
	public String getOptionalSuffix() {
		return( getServerMethodRec().getOptionalSuffix() );
	}

	@Override
	public void setOptionalSuffix( String value ) {
		if( getServerMethodRec().getOptionalSuffix() != value ) {
			getServerMethodRec().setOptionalSuffix( value );
		}
	}

	@Override
	public boolean getRequiredIsInstanceMethod() {
		return( getServerMethodRec().getRequiredIsInstanceMethod() );
	}

	@Override
	public void setRequiredIsInstanceMethod( boolean value ) {
		if( getServerMethodRec().getRequiredIsInstanceMethod() != value ) {
			getServerMethodRec().setRequiredIsInstanceMethod( value );
		}
	}

	@Override
	public boolean getRequiredIsServerOnly() {
		return( getServerMethodRec().getRequiredIsServerOnly() );
	}

	@Override
	public void setRequiredIsServerOnly( boolean value ) {
		if( getServerMethodRec().getRequiredIsServerOnly() != value ) {
			getServerMethodRec().setRequiredIsServerOnly( value );
		}
	}

	@Override
	public String getRequiredJMethodBody() {
		return( getServerMethodRec().getRequiredJMethodBody() );
	}

	@Override
	public void setRequiredJMethodBody( String value ) {
		if( getServerMethodRec().getRequiredJMethodBody() != value ) {
			getServerMethodRec().setRequiredJMethodBody( value );
		}
	}

	@Override
	public String getRequiredCppMethodBody() {
		return( getServerMethodRec().getRequiredCppMethodBody() );
	}

	@Override
	public void setRequiredCppMethodBody( String value ) {
		if( getServerMethodRec().getRequiredCppMethodBody() != value ) {
			getServerMethodRec().setRequiredCppMethodBody( value );
		}
	}

	@Override
	public String getRequiredCsMethodBody() {
		return( getServerMethodRec().getRequiredCsMethodBody() );
	}

	@Override
	public void setRequiredCsMethodBody( String value ) {
		if( getServerMethodRec().getRequiredCsMethodBody() != value ) {
			getServerMethodRec().setRequiredCsMethodBody( value );
		}
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getServerMethodRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsServerMethod().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getServerMethodRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getServerMethodRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getServerMethodRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getServerMethodRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public ICFBamTableObj getRequiredContainerForTable() {
		return( getRequiredContainerForTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerForTable( boolean forceRead ) {
		if( forceRead || ( requiredContainerForTable == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamTableObj obj = ((ICFBamSchemaObj)getOrigAsServerMethod().getSchema()).getTableTableObj().readTableByIdIdx( getServerMethodRec().getRequiredTableId() );
				requiredContainerForTable = obj;
				if( obj != null ) {
					requiredContainerForTable = obj;
				}
			}
		}
		return( requiredContainerForTable );
	}

	@Override
	public void setRequiredContainerForTable( ICFBamTableObj value ) {
		if( rec == null ) {
			getServerMethodRec();
		}
		if( value != null ) {
			requiredContainerForTable = value;
			getServerMethodRec().setRequiredContainerForTable(value.getTableRec());
		}
		requiredContainerForTable = value;
	}

	@Override
	public List<ICFBamParamObj> getOptionalComponentsParams() {
		List<ICFBamParamObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByServerMethodIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamParamObj> getOptionalComponentsParams( boolean forceRead ) {
		List<ICFBamParamObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByServerMethodIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamServerMethod origRec = getOrigAsServerMethod().getServerMethodRec();
		ICFBamServerMethod myRec = getServerMethodRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamServerMethod origRec = getOrigAsServerMethod().getServerMethodRec();
		ICFBamServerMethod myRec = getServerMethodRec();
		myRec.set( origRec );
	}
}
