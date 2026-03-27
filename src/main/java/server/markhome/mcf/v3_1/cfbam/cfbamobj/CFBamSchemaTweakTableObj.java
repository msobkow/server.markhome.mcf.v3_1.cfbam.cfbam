// Description: Java 25 Table Object implementation for SchemaTweak.

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

public class CFBamSchemaTweakTableObj
	implements ICFBamSchemaTweakTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamSchemaTweak.CLASS_CODE;
	protected static final int backingClassCode = ICFBamSchemaTweak.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> members;
	private Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> allSchemaTweak;
	private Map< ICFBamTweakByUNameIdxKey,
		ICFBamSchemaTweakObj > indexByUNameIdx;
	private Map< ICFBamTweakByValTentIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > > indexByValTentIdx;
	private Map< ICFBamTweakByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > > indexByScopeIdx;
	private Map< ICFBamTweakByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > > indexByDefSchemaIdx;
	private Map< ICFBamSchemaTweakBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "SchemaTweak";
	public static String TABLE_DBNAME = "schtwk";

	public CFBamSchemaTweakTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
		allSchemaTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamSchemaTweakTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
		allSchemaTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexBySchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamSchemaTweakTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamSchemaTweakTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSchemaTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamSchemaTweakObj> toForget = new LinkedList<ICFBamSchemaTweakObj>();
		ICFBamSchemaTweakObj cur = null;
		Iterator<ICFBamSchemaTweakObj> iter = members.values().iterator();
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
	 *	CFBamSchemaTweakObj.
	 */
	@Override
	public ICFBamSchemaTweakObj newInstance() {
		ICFBamSchemaTweakObj inst = new CFBamSchemaTweakObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaTweakObj.
	 */
	@Override
	public ICFBamSchemaTweakEditObj newEditInstance( ICFBamSchemaTweakObj orig ) {
		ICFBamSchemaTweakEditObj edit = new CFBamSchemaTweakEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamSchemaTweakObj realiseSchemaTweak( ICFBamSchemaTweakObj Obj ) {
		ICFBamSchemaTweakObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaTweakObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamSchemaTweakObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					indexByValTentIdx.remove( keyValTentIdx );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaTweakBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.remove( keepObj.getPKey() );
					if( mapSchemaIdx.size() <= 0 ) {
						indexBySchemaIdx.remove( keySchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().realiseTweak( Obj );

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
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaTweakBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSchemaTweak != null ) {
				allSchemaTweak.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().realiseTweak( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSchemaTweak != null ) {
				allSchemaTweak.put( keepObj.getPKey(), keepObj );
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
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaTweakBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamSchemaTweakObj createSchemaTweak( ICFBamSchemaTweakObj Obj ) {
		ICFBamSchemaTweakObj obj = Obj;
		ICFBamSchemaTweak rec = obj.getSchemaTweakRec();
		schema.getCFBamBackingStore().getTableSchemaTweak().createSchemaTweak(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamSchemaTweakObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamSchemaTweakObj readSchemaTweak( CFLibDbKeyHash256 pkey ) {
		return( readSchemaTweak( pkey, false ) );
	}

	@Override
	public ICFBamSchemaTweakObj readSchemaTweak( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamSchemaTweakObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamSchemaTweak readRec = schema.getCFBamBackingStore().getTableSchemaTweak().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamSchemaTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamSchemaTweakObj readCachedSchemaTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaTweakObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSchemaTweak( ICFBamSchemaTweakObj obj )
	{
		final String S_ProcName = "CFBamSchemaTweakTableObj.reallyDeepDisposeSchemaTweak() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaTweakObj existing = readCachedSchemaTweak( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamSchemaTweakBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
		keySchemaIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );



		if( indexBySchemaIdx != null ) {
			if( indexBySchemaIdx.containsKey( keySchemaIdx ) ) {
				indexBySchemaIdx.get( keySchemaIdx ).remove( pkey );
				if( indexBySchemaIdx.get( keySchemaIdx ).size() <= 0 ) {
					indexBySchemaIdx.remove( keySchemaIdx );
				}
			}
		}


		schema.getTweakTableObj().reallyDeepDisposeTweak( obj );
	}
	@Override
	public void deepDisposeSchemaTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaTweakObj obj = readCachedSchemaTweak( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamSchemaTweakObj lockSchemaTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaTweakObj locked = null;
		ICFBamSchemaTweak lockRec = schema.getCFBamBackingStore().getTableSchemaTweak().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamSchemaTweakObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSchemaTweak", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readAllSchemaTweak() {
		return( readAllSchemaTweak( false ) );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readAllSchemaTweak( boolean forceRead ) {
		final String S_ProcName = "readAllSchemaTweak";
		if( ( allSchemaTweak == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> map = new HashMap<CFLibDbKeyHash256,ICFBamSchemaTweakObj>();
			allSchemaTweak = map;
			ICFBamSchemaTweak[] recList = schema.getCFBamBackingStore().getTableSchemaTweak().readAllDerived( null );
			ICFBamSchemaTweak rec;
			ICFBamSchemaTweakObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaTweakObj realised = (ICFBamSchemaTweakObj)obj.realise();
			}
		}
		int len = allSchemaTweak.size();
		ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
		Iterator<ICFBamSchemaTweakObj> valIter = allSchemaTweak.values().iterator();
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
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
		List<ICFBamSchemaTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readCachedAllSchemaTweak() {
		final String S_ProcName = "readCachedAllSchemaTweak";
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>();
		if( allSchemaTweak != null ) {
			int len = allSchemaTweak.size();
			ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
			Iterator<ICFBamSchemaTweakObj> valIter = allSchemaTweak.values().iterator();
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
		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
	public ICFBamSchemaTweakObj readSchemaTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readSchemaTweakByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamSchemaTweakObj readSchemaTweakByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamSchemaTweakObj obj = readSchemaTweak( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamSchemaTweakObj readSchemaTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readSchemaTweakByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamSchemaTweakObj readSchemaTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamSchemaTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamSchemaTweakObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readSchemaTweakByValTentIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByValTentIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( ( ! forceRead ) && indexByValTentIdx.containsKey( key ) ) {
			dict = indexByValTentIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByValTentIdx( null,
				TenantId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaTweakObj realised = (ICFBamSchemaTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByValTentIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
		Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
		List<ICFBamSchemaTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readSchemaTweakByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaTweakObj realised = (ICFBamSchemaTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
		Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
		List<ICFBamSchemaTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readSchemaTweakByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaTweakObj realised = (ICFBamSchemaTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
		Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
		List<ICFBamSchemaTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readSchemaTweakBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaTweakObj> readSchemaTweakBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaTweakBySchemaIdx";
		ICFBamSchemaTweakBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaTweakBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaTweakObj>();
			ICFBamSchemaTweakObj obj;
			ICFBamSchemaTweak[] recList = schema.getCFBamBackingStore().getTableSchemaTweak().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamSchemaTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaTweakObj realised = (ICFBamSchemaTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
		Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
		List<ICFBamSchemaTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaTweakObj readCachedSchemaTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaTweakObj obj = null;
		obj = readCachedSchemaTweak( Id );
		return( obj );
	}

	@Override
	public ICFBamSchemaTweakObj readCachedSchemaTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamSchemaTweakObj obj = null;
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
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
			Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
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
	public List<ICFBamSchemaTweakObj> readCachedSchemaTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedSchemaTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>();
		if( indexByValTentIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
			if( indexByValTentIdx.containsKey( key ) ) {
				dict = indexByValTentIdx.get( key );
				int len = dict.size();
				ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
				Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
			ICFBamSchemaTweakObj obj;
			Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
	public List<ICFBamSchemaTweakObj> readCachedSchemaTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedSchemaTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
				Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
			ICFBamSchemaTweakObj obj;
			Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
	public List<ICFBamSchemaTweakObj> readCachedSchemaTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedSchemaTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
				Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
			ICFBamSchemaTweakObj obj;
			Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
	public List<ICFBamSchemaTweakObj> readCachedSchemaTweakBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedSchemaTweakBySchemaIdx";
		ICFBamSchemaTweakBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamSchemaTweakObj> arrayList = new ArrayList<ICFBamSchemaTweakObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaTweakObj arr[] = new ICFBamSchemaTweakObj[len];
				Iterator<ICFBamSchemaTweakObj> valIter = dict.values().iterator();
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
			ICFBamSchemaTweakObj obj;
			Iterator<ICFBamSchemaTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaTweakObj> cmp = new Comparator<ICFBamSchemaTweakObj>() {
			@Override
			public int compare( ICFBamSchemaTweakObj lhs, ICFBamSchemaTweakObj rhs ) {
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
	public void deepDisposeSchemaTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaTweakObj obj = readCachedSchemaTweakByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamSchemaTweakObj obj = readCachedSchemaTweakByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeSchemaTweakByValTentIdx";
		ICFBamSchemaTweakObj obj;
		List<ICFBamSchemaTweakObj> arrayList = readCachedSchemaTweakByValTentIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeSchemaTweakByScopeIdx";
		ICFBamSchemaTweakObj obj;
		List<ICFBamSchemaTweakObj> arrayList = readCachedSchemaTweakByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeSchemaTweakByDefSchemaIdx";
		ICFBamSchemaTweakObj obj;
		List<ICFBamSchemaTweakObj> arrayList = readCachedSchemaTweakByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaTweakBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeSchemaTweakBySchemaIdx";
		ICFBamSchemaTweakObj obj;
		List<ICFBamSchemaTweakObj> arrayList = readCachedSchemaTweakBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamSchemaTweakObj updateSchemaTweak( ICFBamSchemaTweakObj Obj ) {
		ICFBamSchemaTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaTweak().updateSchemaTweak( null,
			Obj.getSchemaTweakRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getSchemaTweakTableObj().getClassCode() ) {
			obj = (ICFBamSchemaTweakObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSchemaTweak( ICFBamSchemaTweakObj Obj ) {
		ICFBamSchemaTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweak( null,
			obj.getSchemaTweakRec() );
		Obj.forget();
	}

	@Override
	public void deleteSchemaTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaTweakObj obj = readSchemaTweak(Id);
		if( obj != null ) {
			ICFBamSchemaTweakEditObj editObj = (ICFBamSchemaTweakEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamSchemaTweakEditObj)obj.beginEdit();
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
		deepDisposeSchemaTweakByIdIdx( Id );
	}

	@Override
	public void deleteSchemaTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamSchemaTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamSchemaTweakObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeSchemaTweakByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteSchemaTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( indexByValTentIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict = indexByValTentIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByValTentIdx( null,
				TenantId );
			Iterator<ICFBamSchemaTweakObj> iter = dict.values().iterator();
			ICFBamSchemaTweakObj obj;
			List<ICFBamSchemaTweakObj> toForget = new LinkedList<ICFBamSchemaTweakObj>();
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
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByValTentIdx( null,
				TenantId );
		}
		deepDisposeSchemaTweakByValTentIdx( TenantId );
	}

	@Override
	public void deleteSchemaTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamSchemaTweakObj> iter = dict.values().iterator();
			ICFBamSchemaTweakObj obj;
			List<ICFBamSchemaTweakObj> toForget = new LinkedList<ICFBamSchemaTweakObj>();
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
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByScopeIdx( null,
				ScopeId );
		}
		deepDisposeSchemaTweakByScopeIdx( ScopeId );
	}

	@Override
	public void deleteSchemaTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamSchemaTweakObj> iter = dict.values().iterator();
			ICFBamSchemaTweakObj obj;
			List<ICFBamSchemaTweakObj> toForget = new LinkedList<ICFBamSchemaTweakObj>();
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
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeSchemaTweakByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteSchemaTweakBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamSchemaTweakBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaTweak().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaTweakBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaTweakObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaTweakObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamSchemaTweakObj> iter = dict.values().iterator();
			ICFBamSchemaTweakObj obj;
			List<ICFBamSchemaTweakObj> toForget = new LinkedList<ICFBamSchemaTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaTweak().deleteSchemaTweakBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeSchemaTweakBySchemaIdx( SchemaDefId );
	}
}