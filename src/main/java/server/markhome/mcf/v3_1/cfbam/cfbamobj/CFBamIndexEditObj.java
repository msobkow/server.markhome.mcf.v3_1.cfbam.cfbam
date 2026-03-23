// Description: Java 25 edit object instance implementation for CFBam Index.

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
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamIndexEditObj
	extends CFBamScopeEditObj

	implements ICFBamIndexEditObj
{
	protected ICFBamTableObj requiredContainerTable;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamIndexColObj> optionalComponentsColumns;

	public CFBamIndexEditObj( ICFBamIndexObj argOrig ) {
		super( argOrig );
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getIndexTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Index" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTable();
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
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByUNameIdx( getRequiredId(),
				nextName, false );
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
		ICFBamIndexObj retobj = getSchema().getIndexTableObj().realiseIndex( (ICFBamIndexObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsIndex().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamIndexObj retobj = ((ICFBamSchemaObj)getOrigAsIndex().getSchema()).getIndexTableObj().createIndex( getOrigAsIndex() );
		if( retobj == getOrigAsIndex() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getIndexTableObj().updateIndex( (ICFBamIndexObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getIndexTableObj().deleteIndex( getOrigAsIndex() );
		return( null );
	}

	@Override
	public ICFBamIndexTableObj getIndexTable() {
		return( orig.getSchema().getIndexTableObj() );
	}

	@Override
	public ICFBamIndexEditObj getEditAsIndex() {
		return( (ICFBamIndexEditObj)this );
	}

	@Override
	public ICFBamIndexObj getOrigAsIndex() {
		return( (ICFBamIndexObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsIndex().getSchema().getCFBamBackingStore().getFactoryIndex().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerTable = null;
			optionalLookupDefSchema = null;
		}
	}

	@Override
	public ICFBamIndex getIndexRec() {
		return( (ICFBamIndex)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getIndexRec().getRequiredTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getIndexRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getIndexRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getIndexRec().getRequiredName() != value ) {
			getIndexRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getIndexRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getIndexRec().getOptionalShortName() != value ) {
			getIndexRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getIndexRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getIndexRec().getOptionalLabel() != value ) {
			getIndexRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getIndexRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getIndexRec().getOptionalShortDescription() != value ) {
			getIndexRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getIndexRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getIndexRec().getOptionalDescription() != value ) {
			getIndexRec().setOptionalDescription( value );
		}
	}

	@Override
	public String getOptionalDbName() {
		return( getIndexRec().getOptionalDbName() );
	}

	@Override
	public void setOptionalDbName( String value ) {
		if( getIndexRec().getOptionalDbName() != value ) {
			getIndexRec().setOptionalDbName( value );
		}
	}

	@Override
	public String getOptionalSuffix() {
		return( getIndexRec().getOptionalSuffix() );
	}

	@Override
	public void setOptionalSuffix( String value ) {
		if( getIndexRec().getOptionalSuffix() != value ) {
			getIndexRec().setOptionalSuffix( value );
		}
	}

	@Override
	public boolean getRequiredIsUnique() {
		return( getIndexRec().getRequiredIsUnique() );
	}

	@Override
	public void setRequiredIsUnique( boolean value ) {
		if( getIndexRec().getRequiredIsUnique() != value ) {
			getIndexRec().setRequiredIsUnique( value );
		}
	}

	@Override
	public boolean getRequiredIsDbMapped() {
		return( getIndexRec().getRequiredIsDbMapped() );
	}

	@Override
	public void setRequiredIsDbMapped( boolean value ) {
		if( getIndexRec().getRequiredIsDbMapped() != value ) {
			getIndexRec().setRequiredIsDbMapped( value );
		}
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( forceRead || ( requiredContainerTable == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamTableObj obj = ((ICFBamSchemaObj)getOrigAsIndex().getSchema()).getTableTableObj().readTableByIdIdx( getIndexRec().getRequiredTableId() );
				requiredContainerTable = obj;
				if( obj != null ) {
					requiredContainerTable = obj;
				}
			}
		}
		return( requiredContainerTable );
	}

	@Override
	public void setRequiredContainerTable( ICFBamTableObj value ) {
		if( rec == null ) {
			getIndexRec();
		}
		if( value != null ) {
			requiredContainerTable = value;
			getIndexRec().setRequiredContainerTable(value.getTableRec());
		}
		requiredContainerTable = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getIndexRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsIndex().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getIndexRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getIndexRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getIndexRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getIndexRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalComponentsColumns() {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIndexIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalComponentsColumns( boolean forceRead ) {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIndexIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamIndex origRec = getOrigAsIndex().getIndexRec();
		ICFBamIndex myRec = getIndexRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamIndex origRec = getOrigAsIndex().getIndexRec();
		ICFBamIndex myRec = getIndexRec();
		myRec.set( origRec );
	}
}
