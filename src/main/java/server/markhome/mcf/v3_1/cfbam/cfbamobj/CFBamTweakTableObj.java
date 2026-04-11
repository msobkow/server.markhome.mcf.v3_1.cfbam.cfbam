// Description: Java 25 Table Object implementation for Tweak.

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

public class CFBamTweakTableObj
	implements ICFBamTweakTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamTweak.CLASS_CODE;
	protected static final int backingClassCode = ICFBamTweak.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamTweakObj> members;
	private Map<CFLibDbKeyHash256, ICFBamTweakObj> allTweak;
	private Map< ICFBamTweakByUNameIdxKey,
		ICFBamTweakObj > indexByUNameIdx;
	private Map< ICFBamTweakByValTentIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTweakObj > > indexByValTentIdx;
	private Map< ICFBamTweakByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTweakObj > > indexByScopeIdx;
	private Map< ICFBamTweakByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamTweakObj > > indexByDefSchemaIdx;
	private Map< ICFBamTweakByUDefIdxKey,
		ICFBamTweakObj > indexByUDefIdx;
	public static String TABLE_NAME = "Tweak";
	public static String TABLE_DBNAME = "tweakdef";

	public CFBamTweakTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTweakObj>();
		allTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
	}

	public CFBamTweakTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamTweakObj>();
		allTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamTweakTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamTweakTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		List<ICFBamTweakObj> toForget = new LinkedList<ICFBamTweakObj>();
		ICFBamTweakObj cur = null;
		Iterator<ICFBamTweakObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTweakObj.
	 */
	@Override
	public ICFBamTweakObj newInstance() {
		ICFBamTweakObj inst = new CFBamTweakObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamTweakObj.
	 */
	@Override
	public ICFBamTweakEditObj newEditInstance( ICFBamTweakObj orig ) {
		ICFBamTweakEditObj edit = new CFBamTweakEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamTweakObj constructByClassCode( int backingClassCode ) {
		ICFBamTweakObj obj = null;
		if( backingClassCode == ICFBamTweak.CLASS_CODE) {
			obj = ((ICFBamSchemaObj)schema).getTweakTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTableTweak.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTableTweakTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamSchemaTweak.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getSchemaTweakTableObj().newInstance();
		}
		return( obj );
	}

	@Override
	public ICFBamTweakObj realiseTweak( ICFBamTweakObj Obj ) {
		ICFBamTweakObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTweakObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamTweakObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.remove( keepObj.getPKey() );
					if( mapValTentIdx.size() <= 0 ) {
						indexByValTentIdx.remove( keyValTentIdx );
					}
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.remove( keepObj.getPKey() );
					if( mapScopeIdx.size() <= 0 ) {
						indexByScopeIdx.remove( keyScopeIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.remove( keyUDefIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( allTweak != null ) {
				allTweak.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTweak != null ) {
				allTweak.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamTweakObj createTweak( ICFBamTweakObj Obj ) {
		ICFBamTweakObj obj = Obj;
		ICFBamTweak rec = obj.getTweakRec();
		schema.getCFBamBackingStore().getTableTweak().createTweak(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamTweakObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamTweakObj readTweak( CFLibDbKeyHash256 pkey ) {
		return( readTweak( pkey, false ) );
	}

	@Override
	public ICFBamTweakObj readTweak( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamTweakObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamTweak readRec = schema.getCFBamBackingStore().getTableTweak().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTweakObj readCachedTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTweakObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTweak( ICFBamTweakObj obj )
	{
		final String S_ProcName = "CFBamTweakTableObj.reallyDeepDisposeTweak() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamTweakObj existing = readCachedTweak( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamTweakByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		keyUNameIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamTweakByValTentIdxKey keyValTentIdx = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		keyValTentIdx.setRequiredTenantId( existing.getRequiredTenantId() );

		ICFBamTweakByScopeIdxKey keyScopeIdx = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		keyScopeIdx.setRequiredScopeId( existing.getRequiredScopeId() );

		ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamTweakByUDefIdxKey keyUDefIdx = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		keyUDefIdx.setRequiredTenantId( existing.getRequiredTenantId() );
		keyUDefIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyUDefIdx.setOptionalDefSchemaTenantId( existing.getOptionalDefSchemaTenantId() );
		keyUDefIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );
		keyUDefIdx.setRequiredName( existing.getRequiredName() );



		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByValTentIdx != null ) {
			if( indexByValTentIdx.containsKey( keyValTentIdx ) ) {
				indexByValTentIdx.get( keyValTentIdx ).remove( pkey );
				if( indexByValTentIdx.get( keyValTentIdx ).size() <= 0 ) {
					indexByValTentIdx.remove( keyValTentIdx );
				}
			}
		}

		if( indexByScopeIdx != null ) {
			if( indexByScopeIdx.containsKey( keyScopeIdx ) ) {
				indexByScopeIdx.get( keyScopeIdx ).remove( pkey );
				if( indexByScopeIdx.get( keyScopeIdx ).size() <= 0 ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByUDefIdx != null ) {
			indexByUDefIdx.remove( keyUDefIdx );
		}


	}
	@Override
	public void deepDisposeTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTweakObj obj = readCachedTweak( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTweakObj lockTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamTweakObj locked = null;
		ICFBamTweak lockRec = schema.getCFBamBackingStore().getTableTweak().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamTweakObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTweak", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamTweakObj> readAllTweak() {
		return( readAllTweak( false ) );
	}

	@Override
	public List<ICFBamTweakObj> readAllTweak( boolean forceRead ) {
		final String S_ProcName = "readAllTweak";
		if( ( allTweak == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> map = new HashMap<CFLibDbKeyHash256,ICFBamTweakObj>();
			allTweak = map;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readAllDerived( null );
			ICFBamTweak rec;
			ICFBamTweakObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTweakObj realised = (ICFBamTweakObj)obj.realise();
			}
		}
		int len = allTweak.size();
		ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
		Iterator<ICFBamTweakObj> valIter = allTweak.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTweakObj> readCachedAllTweak() {
		final String S_ProcName = "readCachedAllTweak";
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>();
		if( allTweak != null ) {
			int len = allTweak.size();
			ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
			Iterator<ICFBamTweakObj> valIter = allTweak.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTweakObj readTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readTweakByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamTweakObj readTweakByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamTweakObj obj = readTweak( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamTweakObj readTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readTweakByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTweakObj readTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTweakObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readTweakByValTentIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByValTentIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( ( ! forceRead ) && indexByValTentIdx.containsKey( key ) ) {
			dict = indexByValTentIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByValTentIdx( null,
				TenantId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTweakObj realised = (ICFBamTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByValTentIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
		Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readTweakByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTweakObj realised = (ICFBamTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
		Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readTweakByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamTweakObj> readTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamTweakObj realised = (ICFBamTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
		Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamTweakObj readTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		return( readTweakByUDefIdx( TenantId,
			ScopeId,
			DefSchemaTenantId,
			DefSchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamTweakObj readTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamTweakObj obj = null;
		if( ( ! forceRead ) && indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamTweakObj readCachedTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTweakObj obj = null;
		obj = readCachedTweak( Id );
		return( obj );
	}

	@Override
	public ICFBamTweakObj readCachedTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTweakObj obj = null;
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamTweakObj> readCachedTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>();
		if( indexByValTentIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
			if( indexByValTentIdx.containsKey( key ) ) {
				dict = indexByValTentIdx.get( key );
				int len = dict.size();
				ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
				Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTweakObj obj;
			Iterator<ICFBamTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTweakObj> readCachedTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
				Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTweakObj obj;
			Iterator<ICFBamTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamTweakObj> readCachedTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamTweakObj> arrayList = new ArrayList<ICFBamTweakObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamTweakObj arr[] = new ICFBamTweakObj[len];
				Iterator<ICFBamTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamTweakObj obj;
			Iterator<ICFBamTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamTweakObj> cmp = new Comparator<ICFBamTweakObj>() {
			@Override
			public int compare( ICFBamTweakObj lhs, ICFBamTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamTweakObj readCachedTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamTweakObj obj = null;
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		if( indexByUDefIdx != null ) {
			if( indexByUDefIdx.containsKey( key ) ) {
				obj = indexByUDefIdx.get( key );
			}
			else {
				Iterator<ICFBamTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTweakObj obj = readCachedTweakByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamTweakObj obj = readCachedTweakByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeTweakByValTentIdx";
		ICFBamTweakObj obj;
		List<ICFBamTweakObj> arrayList = readCachedTweakByValTentIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeTweakByScopeIdx";
		ICFBamTweakObj obj;
		List<ICFBamTweakObj> arrayList = readCachedTweakByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeTweakByDefSchemaIdx";
		ICFBamTweakObj obj;
		List<ICFBamTweakObj> arrayList = readCachedTweakByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamTweakObj obj = readCachedTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamTweakObj updateTweak( ICFBamTweakObj Obj ) {
		ICFBamTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableTweak().updateTweak( null,
			Obj.getTweakRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getTweakTableObj().getClassCode() ) {
			obj = (ICFBamTweakObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTweak( ICFBamTweakObj Obj ) {
		ICFBamTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableTweak().deleteTweak( null,
			obj.getTweakRec() );
		Obj.forget();
	}

	@Override
	public void deleteTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamTweakObj obj = readTweak(Id);
		if( obj != null ) {
			ICFBamTweakEditObj editObj = (ICFBamTweakEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamTweakEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeTweakByIdIdx( Id );
	}

	@Override
	public void deleteTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamTweakObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeTweakByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( indexByValTentIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict = indexByValTentIdx.get( key );
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByValTentIdx( null,
				TenantId );
			Iterator<ICFBamTweakObj> iter = dict.values().iterator();
			ICFBamTweakObj obj;
			List<ICFBamTweakObj> toForget = new LinkedList<ICFBamTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByValTentIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByValTentIdx( null,
				TenantId );
		}
		deepDisposeTweakByValTentIdx( TenantId );
	}

	@Override
	public void deleteTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamTweakObj> iter = dict.values().iterator();
			ICFBamTweakObj obj;
			List<ICFBamTweakObj> toForget = new LinkedList<ICFBamTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByScopeIdx( null,
				ScopeId );
		}
		deepDisposeTweakByScopeIdx( ScopeId );
	}

	@Override
	public void deleteTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamTweakObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamTweakObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamTweakObj> iter = dict.values().iterator();
			ICFBamTweakObj obj;
			List<ICFBamTweakObj> toForget = new LinkedList<ICFBamTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeTweakByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamTweakObj obj = null;
		if( indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableTweak().deleteTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		}
		deepDisposeTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
	}
}