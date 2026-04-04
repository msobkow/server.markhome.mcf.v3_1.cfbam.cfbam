// Description: Java 25 edit object instance implementation for CFBam SchemaDef.

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

public class CFBamSchemaDefEditObj
	extends CFBamScopeEditObj

	implements ICFBamSchemaDefEditObj
{
	protected ICFIntMinorVersionObj requiredContainerMinorVersion;
	protected List<ICFBamTableObj> optionalComponentsTables;
	protected List<ICFBamValueObj> optionalComponentsTypes;
	protected List<ICFBamSchemaRefObj> optionalComponentsSchemaRefs;
	protected List<ICFBamTweakObj> optionalComponentsTweaks;
	protected ICFSecTenantObj requiredOwnerCTenant;

	public CFBamSchemaDefEditObj( ICFBamSchemaDefObj argOrig ) {
		super( argOrig );
		requiredContainerMinorVersion = null;
		requiredOwnerCTenant = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getSchemaDefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SchemaDef" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFIntMinorVersionObj scope = getRequiredContainerMinorVersion();
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
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
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
			else if( container instanceof ICFBamSubProjectObj ) {
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
		ICFBamSchemaDefObj retobj = getSchema().getSchemaDefTableObj().realiseSchemaDef( (ICFBamSchemaDefObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSchemaDef().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamSchemaDefObj retobj = ((ICFBamSchemaObj)getOrigAsSchemaDef().getSchema()).getSchemaDefTableObj().createSchemaDef( getOrigAsSchemaDef() );
		if( retobj == getOrigAsSchemaDef() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getSchemaDefTableObj().updateSchemaDef( (ICFBamSchemaDefObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSchemaDefTableObj().deleteSchemaDef( getOrigAsSchemaDef() );
		return( null );
	}

	@Override
	public ICFBamSchemaDefTableObj getSchemaDefTable() {
		return( orig.getSchema().getSchemaDefTableObj() );
	}

	@Override
	public ICFBamSchemaDefEditObj getEditAsSchemaDef() {
		return( (ICFBamSchemaDefEditObj)this );
	}

	@Override
	public ICFBamSchemaDefObj getOrigAsSchemaDef() {
		return( (ICFBamSchemaDefObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsSchemaDef().getSchema().getCFBamBackingStore().getFactorySchemaDef().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerMinorVersion = null;
			requiredOwnerCTenant = null;
		}
	}

	@Override
	public ICFBamSchemaDef getSchemaDefRec() {
		return( (ICFBamSchemaDef)getRec() );
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
	public CFLibDbKeyHash256 getRequiredCTenantId() {
		return( getSchemaDefRec().getRequiredCTenantId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredMinorVersionId() {
		return( getSchemaDefRec().getRequiredMinorVersionId() );
	}

	@Override
	public String getRequiredName() {
		return( getSchemaDefRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getSchemaDefRec().getRequiredName() != value ) {
			getSchemaDefRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalDbName() {
		return( getSchemaDefRec().getOptionalDbName() );
	}

	@Override
	public void setOptionalDbName( String value ) {
		if( getSchemaDefRec().getOptionalDbName() != value ) {
			getSchemaDefRec().setOptionalDbName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getSchemaDefRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getSchemaDefRec().getOptionalShortName() != value ) {
			getSchemaDefRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getSchemaDefRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getSchemaDefRec().getOptionalLabel() != value ) {
			getSchemaDefRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getSchemaDefRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getSchemaDefRec().getOptionalShortDescription() != value ) {
			getSchemaDefRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getSchemaDefRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getSchemaDefRec().getOptionalDescription() != value ) {
			getSchemaDefRec().setOptionalDescription( value );
		}
	}

	@Override
	public String getRequiredCopyrightPeriod() {
		return( getSchemaDefRec().getRequiredCopyrightPeriod() );
	}

	@Override
	public void setRequiredCopyrightPeriod( String value ) {
		if( getSchemaDefRec().getRequiredCopyrightPeriod() != value ) {
			getSchemaDefRec().setRequiredCopyrightPeriod( value );
		}
	}

	@Override
	public String getRequiredCopyrightHolder() {
		return( getSchemaDefRec().getRequiredCopyrightHolder() );
	}

	@Override
	public void setRequiredCopyrightHolder( String value ) {
		if( getSchemaDefRec().getRequiredCopyrightHolder() != value ) {
			getSchemaDefRec().setRequiredCopyrightHolder( value );
		}
	}

	@Override
	public String getRequiredAuthorEMail() {
		return( getSchemaDefRec().getRequiredAuthorEMail() );
	}

	@Override
	public void setRequiredAuthorEMail( String value ) {
		if( getSchemaDefRec().getRequiredAuthorEMail() != value ) {
			getSchemaDefRec().setRequiredAuthorEMail( value );
		}
	}

	@Override
	public String getRequiredProjectURL() {
		return( getSchemaDefRec().getRequiredProjectURL() );
	}

	@Override
	public void setRequiredProjectURL( String value ) {
		if( getSchemaDefRec().getRequiredProjectURL() != value ) {
			getSchemaDefRec().setRequiredProjectURL( value );
		}
	}

	@Override
	public String getRequiredPublishURI() {
		return( getSchemaDefRec().getRequiredPublishURI() );
	}

	@Override
	public void setRequiredPublishURI( String value ) {
		if( getSchemaDefRec().getRequiredPublishURI() != value ) {
			getSchemaDefRec().setRequiredPublishURI( value );
		}
	}

	@Override
	public ICFIntMinorVersionObj getRequiredContainerMinorVersion() {
		return( getRequiredContainerMinorVersion( false ) );
	}

	@Override
	public ICFIntMinorVersionObj getRequiredContainerMinorVersion( boolean forceRead ) {
		if( forceRead || ( requiredContainerMinorVersion == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFIntMinorVersionObj obj = ((ICFBamSchemaObj)getOrigAsSchemaDef().getSchema()).getMinorVersionTableObj().readMinorVersionByIdIdx( getSchemaDefRec().getRequiredMinorVersionId() );
				requiredContainerMinorVersion = obj;
				if( obj != null ) {
					requiredContainerMinorVersion = obj;
				}
			}
		}
		return( requiredContainerMinorVersion );
	}

	@Override
	public void setRequiredContainerMinorVersion( ICFIntMinorVersionObj value ) {
		if( rec == null ) {
			getSchemaDefRec();
		}
		if( value != null ) {
			requiredContainerMinorVersion = value;
			getSchemaDefRec().setRequiredContainerMinorVersion(value.getMinorVersionRec());
		}
		requiredContainerMinorVersion = value;
	}

	@Override
	public List<ICFBamTableObj> getOptionalComponentsTables() {
		List<ICFBamTableObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableBySchemaDefIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTableObj> getOptionalComponentsTables( boolean forceRead ) {
		List<ICFBamTableObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableBySchemaDefIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsTypes() {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsTypes( boolean forceRead ) {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRefObj> getOptionalComponentsSchemaRefs() {
		List<ICFBamSchemaRefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefBySchemaIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaRefObj> getOptionalComponentsSchemaRefs( boolean forceRead ) {
		List<ICFBamSchemaRefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefBySchemaIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks() {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks( boolean forceRead ) {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerCTenant() {
		return( getRequiredOwnerCTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerCTenant( boolean forceRead ) {
		if( forceRead || ( requiredOwnerCTenant == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecTenantObj obj = ((ICFBamSchemaObj)getOrigAsSchemaDef().getSchema()).getTenantTableObj().readTenantByIdIdx( getSchemaDefRec().getRequiredCTenantId() );
				requiredOwnerCTenant = obj;
			}
		}
		return( requiredOwnerCTenant );
	}

	@Override
	public void setRequiredOwnerCTenant( ICFSecTenantObj value ) {
		if( rec == null ) {
			getSchemaDefRec();
		}
		if( value != null ) {
			requiredOwnerCTenant = value;
			getSchemaDefRec().setRequiredOwnerCTenant(value.getTenantRec());
		}
		requiredOwnerCTenant = value;
			super.setRequiredOwnerTenant( value );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamSchemaDef origRec = getOrigAsSchemaDef().getSchemaDefRec();
		ICFBamSchemaDef myRec = getSchemaDefRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamSchemaDef origRec = getOrigAsSchemaDef().getSchemaDefRec();
		ICFBamSchemaDef myRec = getSchemaDefRec();
		myRec.set( origRec );
	}
}
